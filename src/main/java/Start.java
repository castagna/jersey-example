import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import com.talis.labs.jersey.Run;

 
public class Start {
	  private static final String myURL = myURL();
  public static void main(String[] args) throws Exception {
	  System.out.println("HERE!");
	  
	  final ClassLoader cl = getClassLoader();
	  Thread.currentThread().setContextClassLoader(cl);
	  run(cl, args);
	  
	  return;
	  
  }
  
  public static void server(String[] args) {
	  System.out.println("HERE2");
	  
	    Server server = new Server();
	    SocketConnector connector = new SocketConnector();
	 
	    // Set some timeout options to make debugging easier.
	    connector.setMaxIdleTime(1000 * 60 * 60);
	    connector.setSoLingerTime(-1);
	    connector.setPort(8080);
	    server.setConnectors(new Connector[] { connector });
	 
	    WebAppContext context = new WebAppContext();
	    context.setServer(server);
	    context.setContextPath("/");
	 
	    ProtectionDomain protectionDomain = Start.class.getProtectionDomain();
	    URL location = protectionDomain.getCodeSource().getLocation();
	    context.setWar(location.toExternalForm());
	 
	    server.setHandler(context);

	    try {
	      server.start();
	      System.in.read();
	      server.stop();
	      server.join();
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.exit(100);
	    }
  }
  
  private static void run (final ClassLoader loader, final String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
	  Class<?> clazz = Class.forName("Start", true, loader);
	  final Method main = clazz.getMethod("server", args.getClass());
	  final Object result = main.invoke(null, new Object[] { args } );
	  
  }
  
  private static ClassLoader getClassLoader() {
	    final ArrayList<URL> paths = new ArrayList<URL>();
	    try {
	      final ZipInputStream zf = openMyZip();
	      try {
	        ZipEntry ze;
	        while ((ze = zf.getNextEntry()) != null) {
	          if (ze.getName().startsWith("WEB-INF/lib/")) {
	            // Try to derive the name of the temporary file so it
	            // doesn't completely suck. Best if we can make it
	            // match the name it was in the WAR.
	            //
	            String name = ze.getName().substring("WEB-INF/lib/".length());
	            if (name.lastIndexOf('/') >= 0) {
	              name = name.substring(name.lastIndexOf('/') + 1);
	            }
	            if (name.lastIndexOf('.') >= 0) {
	              name = name.substring(0, name.lastIndexOf('.'));
	            }
	            if (name.length() == 0) {
	              name = "ewar";
	            }

	            final File tmp = File.createTempFile(name, ".jar");
	            
	            System.out.println(tmp.getAbsolutePath());
	            
	            // tmp.deleteOnExit();
	            final FileOutputStream out = new FileOutputStream(tmp);
	            try {
	              final byte[] buf = new byte[4096];
	              int n;
	              while ((n = zf.read(buf, 0, buf.length)) > 0) {
	                out.write(buf, 0, n);
	              }
	            } finally {
	              out.close();
	            }
	            paths.add(tmp.toURL());
	          }
	          zf.closeEntry();
	        }
	      } finally {
	        zf.close();
	      }
	    } catch (IOException e) {
	      throw new LinkageError("Cannot unpack libs from " + myURL);
	    }
	    
	    System.out.println(paths.size());
	    
	    if (paths.isEmpty()) {
	      return ExecutableWarMain.class.getClassLoader();
	    }
	    
	    ClassLoader cl =new URLClassLoader(paths.toArray(new URL[paths.size()]));
	    
	    try {
			cl.loadClass("org.eclipse.jetty.server.Handler");
			System.out.println("LOADED");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return cl; 
	  }

	  private static ZipInputStream openMyZip() throws IOException,
	      MalformedURLException {
	    final URL u = new URL(myURL);
	    return new ZipInputStream(new BufferedInputStream(u.openStream()));
	  }

	  private static String myURL() {
	    final String myName =
	        Start.class.getName().replace('.', '/') + ".class";
	    final URL u = Start.class.getClassLoader().getResource(myName);
	    if (u == null) {
	      throw new LinkageError("Cannot locate " + myName);
	    }

	    if (!"jar".equals(u.getProtocol())) {
	      throw new LinkageError("Expected jar: URL: " + u);
	    }

	    String path = u.toExternalForm();
	    if (path == null) {
	      throw new LinkageError("Expected jar: URL: " + u);
	    }

	    final int bang = path.indexOf('!');
	    if (bang < 0) {
	      throw new LinkageError("Expected jar: URL: " + u);
	    }

	    return path.substring(4, bang);
	  }

}
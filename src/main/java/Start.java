//import java.net.URL;
//import java.security.ProtectionDomain;
//
//import org.eclipse.jetty.server.Connector;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.bio.SocketConnector;
//import org.eclipse.jetty.webapp.WebAppContext;

 
public class Start {
 
  public static void main(String[] args) throws Exception {
	  System.out.println("HERE!");
	  
//    Server server = new Server();
//    SocketConnector connector = new SocketConnector();
// 
//    // Set some timeout options to make debugging easier.
//    connector.setMaxIdleTime(1000 * 60 * 60);
//    connector.setSoLingerTime(-1);
//    connector.setPort(8080);
//    server.setConnectors(new Connector[] { connector });
// 
//    WebAppContext context = new WebAppContext();
//    context.setServer(server);
//    context.setContextPath("/");
// 
//    ProtectionDomain protectionDomain = Start.class.getProtectionDomain();
//    URL location = protectionDomain.getCodeSource().getLocation();
//    context.setWar(location.toExternalForm());
// 
//    server.setHandler(context);
//
//    try {
//      server.start();
//      System.in.read();
//      server.stop();
//      server.join();
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.exit(100);
//    }
  }
}
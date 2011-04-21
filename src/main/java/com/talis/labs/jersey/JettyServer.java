/*
 * Copyright © 2011 Talis Systems Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.talis.labs.jersey;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {

	public static void main(String[] args) throws Exception {
//        System.setProperty("log4j.debug", "");
//        System.setProperty("log4j.configuration", "src/test/resources/log4j.properties");
		
		Server server = new Server(8080);

//        URL url = Start.class.getClassLoader().getResource("Start.class");
//        File warFile = new File(((JarURLConnection) url.openConnection()).getJarFile().getName());
		
        WebAppContext context = new WebAppContext();

        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
 
        server.setHandler(context);
 
        server.start();
        server.join();
	}

//	private static void startJetty() throws Exception {
//		ServletHolder sh = new ServletHolder(ServletContainer.class);
//		sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
//		sh.setInitParameter("com.sun.jersey.config.property.packages", "com.talis.labs.jersey");
//		Server server = new Server(8080);
//		ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
//		context.addServlet(sh, "/*");
//		server.start();
//        server.join();
//	}

}

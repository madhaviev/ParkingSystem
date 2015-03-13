package com.madhu.parkingUtil;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyStart {

	public static void main(String args[]) throws Exception {
		
		Server server = new Server(8090);
		WebAppContext ctx = new WebAppContext();
		ctx.setContextPath("/parking");
		ctx.setResourceBase("src/main/webapp/");
		ctx.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		
		
		server.setHandler(ctx);
		server.start();
		server.join();
		
	}
}

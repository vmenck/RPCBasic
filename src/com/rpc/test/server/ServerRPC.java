package com.rpc.test.server;

import java.io.IOException;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
//https://www.zugiart.com/2010/09/java-apache-simple-xmlrpc-server/

// https://ws.apache.org/xmlrpc/server.html
// https://ws.apache.org/xmlrpc/client.html

public class ServerRPC 
{
	private Integer port;
	private WebServer webServer = null;
	private PropertyHandlerMapping phm = null;
	private XmlRpcServer xmlRpcServer = null;
	private SimpleXmlRpcRequestHandlerFactory handler = null;

	public ServerRPC(Integer port) throws IOException 
	{
		this.port = port;
		this.webServer = new WebServer(this.port);
		this.xmlRpcServer = this.webServer.getXmlRpcServer();
		this.handler = new SimpleXmlRpcRequestHandlerFactory();
		this.phm = new PropertyHandlerMapping();
		this.phm.setRequestProcessorFactoryFactory(this.handler);
	}

	/**
	 * Adds a handler instance (which CAN be stateful) for each request. Note that
	 * every public method in this object will be callable via xml-rpc client
	 *
	 * @param name - handler name
	 * @param requestHandler - handler obj instance
	 * @throws Exception
	 */
	public void addHandler(String name, Object requestHandler)	throws Exception
	{
		this.handler.setHandler(name, requestHandler);
		this.phm.addHandler(name, requestHandler.getClass());
	}
 
	/**
	 * Start the xml-rpc server forever. In the rare case of fatal exception, the
	 * web server will be restarted automagically. This is a blocking call (not
	 * thread-based).
	 */
	public void serve_forever() throws Exception
	{
		// init
		this.xmlRpcServer.setHandlerMapping(phm);
		XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
		serverConfig.setEnabledForExtensions(true);
		this.webServer.start();
	}
		
	public static void main(String[] args) throws Exception {
		
		ServerRPC server = new ServerRPC(8888);
		server.addHandler("method", new SimpleXMLRpcTest());
		server.serve_forever();
		System.out.println("Start server...");
	}
}
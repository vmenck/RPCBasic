package com.rpc.test.stub;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class RPCClientStub {

	public RPCClientStub() throws MalformedURLException, XmlRpcException {

		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://127.0.0.1:8888"));
		
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		Object result = client.execute("method.hello", new ArrayList<String>());
		
		System.out.println(result);
	}
	
	public static void main(String[] args) throws MalformedURLException, XmlRpcException {
		new RPCClientStub();
	}
}

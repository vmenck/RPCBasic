package com.rpc.test.server;

public class SimpleXMLRpcTest
{
	public String hello()
	{
		System.out.println("print server side..");
		return "return values to the client";
	}
} 
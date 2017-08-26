package com.gobravery.wind.ws;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class WSHandler implements InvocationHandler {
	private RPCServiceClient  serviceClient;
	private WSModel<?> wsModel;
	public WSHandler(RPCServiceClient  serviceClient,WSModel<?> m){
		this.serviceClient=serviceClient;
		this.wsModel=m;
	}
	public Object invoke(Object proxy, Method method, Object[] args,String namespace) throws AxisFault {
		Object ret=null;
		String qname=method.getName();
		QName opQName = new QName(namespace, qname);
		Class<?>[] opReturnType=new Class<?>[]{method.getReturnType()};
		ret=serviceClient.invokeBlocking(opQName, args, opReturnType)[0];
		return ret;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return invoke(proxy, method,args,wsModel.getTargetNamespace());
	}

}

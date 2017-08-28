package com.gobravery.wind.ws;

import java.lang.reflect.Proxy;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public abstract class AbstractServiceProxy implements IServiceProxy{
	
	private WSModel<?> wsModel;
	public AbstractServiceProxy(WSModel<?> ws){
		wsModel=ws;
	}
	//
	public <T> T getService(Class<T> clz) throws Exception{
		RPCServiceClient  serviceClient = new RPCServiceClient();
		EndpointReference targetEPR = new EndpointReference(wsModel.getWsdlLocation());
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);  
        //
        setConf(serviceClient,options,wsModel);
		//
		Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), 
	                new Class[]{clz},
	                new WSHandler(serviceClient,wsModel)
	        );
	    return (T) o;
	}
	//鍏跺畠閰嶇疆
	protected abstract void setConf(RPCServiceClient  serviceClient,Options options,WSModel<?> wsModel); 
}

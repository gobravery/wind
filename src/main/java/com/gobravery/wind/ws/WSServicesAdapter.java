package com.gobravery.wind.ws;

public class WSServicesAdapter {
	public static IServiceProxy getProxy(WSModel<?> ws){
		
		if(ws.getType()==null || ws.getType().equals(WSModel.TYPE_SIMPLE)){
			return new SimpleServiceProxy(ws);
		}
		if(ws.getType().equals(WSModel.TYPE_AXIS2LOGIN)){
			return new Axis2LoginServiceProxy(ws);
		}
		return null;
	}
}

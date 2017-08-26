package com.gobravery.wind.ws;

public interface IServiceProxy {
	public <T> T getService(Class<T> clz) throws Exception;
}

package com.gobravery.wind.server;

import javax.activation.DataHandler;


public interface FileWebservice {
	public String say();
	public long myname(String name);
	public void login(String name);
	public long uploadWithDataHandler(DataHandler file, String filename,long size) ;
}

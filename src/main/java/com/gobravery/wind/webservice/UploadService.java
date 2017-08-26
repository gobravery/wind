package com.gobravery.wind.webservice;

import java.io.IOException;

public interface UploadService {
	public void acceptFile(byte[] file,String name)throws IOException;

}

package com.gobravery.wind.server;

import java.io.IOException;

public interface UploadService {
	public void acceptFile(byte[] file,String name)throws IOException;

}

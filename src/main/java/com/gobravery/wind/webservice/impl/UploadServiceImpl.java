package com.gobravery.wind.webservice.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.gobravery.wind.webservice.UploadService;

public class UploadServiceImpl implements UploadService {

	@Override
	public void acceptFile(byte[] file,String name) throws IOException {
		String path = "d:\\"+name;
        File f=new File(path);
        OutputStream os =new FileOutputStream(f);
        os.write(file);
        os.flush();
        os.close();
	}
}

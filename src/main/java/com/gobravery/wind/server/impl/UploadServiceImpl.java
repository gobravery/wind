package com.gobravery.wind.server.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

import com.gobravery.wind.server.UploadService;
@Component("uploadService")
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

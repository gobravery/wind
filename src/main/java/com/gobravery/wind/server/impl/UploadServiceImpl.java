package com.gobravery.wind.server.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.gobravery.wind.server.UploadService;
import com.gobravery.wind.ws.FileUtils;
@Component("uploadService")
public class UploadServiceImpl implements UploadService {

	@Override
	public void acceptFile(byte[] file,String name) throws IOException {
		String path = "d:\\"+name;
        FileUtils.byteToFile(file, path);
	}
}

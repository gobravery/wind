package com.gobravery.wind.ws;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {
	public static byte[] fileToByte(String path)throws Exception{
		File f=new File(path);
		FileInputStream fis=new FileInputStream(f);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		byte[] buff=new byte[1024];
		int len=-1;
		while((len=fis.read(buff))>-1){
			bos.write(buff,0, len);
		}
		fis.close();
		bos.close();
		return bos.toByteArray();
	}
	public static void byteToFile(byte[] b,String path)throws IOException{
		File f=new File(path);
        OutputStream os =new FileOutputStream(f);
        os.write(b);
        os.flush();
        os.close();
	}
	public static String getName(String path){
		File f=new File(path);
		return f.getName();
	}
}

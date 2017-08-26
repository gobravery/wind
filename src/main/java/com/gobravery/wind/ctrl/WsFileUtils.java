package com.gobravery.wind.ctrl;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.gobravery.wind.client.UploadService;
import com.gobravery.wind.common.SendResult;
import com.gobravery.wind.ws.FileUtils;
import com.gobravery.wind.ws.WSClientFactory;

public class WsFileUtils {
	public SendResult upload(String path)throws Exception{
		UploadService wf=WSClientFactory.get(UploadService.class);
		byte[] b=FileUtils.fileToByte(path);
		wf.acceptFile(b, FileUtils.getName(path));
		return null;
	}
	public SendResult send(String data)throws AxisFault,RemoteException{
		
		
		return null;
	}
	public static void main(String[] args)throws Exception{
		WsFileUtils wfu=new WsFileUtils();
		wfu.upload("D:\\安装包\\44.0.2403.130_chrome_installer.exe");
	}
}

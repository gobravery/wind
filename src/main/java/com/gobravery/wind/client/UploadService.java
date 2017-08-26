package com.gobravery.wind.client;

import java.io.IOException;

import javax.xml.ws.WebServiceClient;
@WebServiceClient(targetNamespace="",wsdlLocation="http://127.0.0.1:8080/wind/?wdsl")
public interface UploadService {
	public void acceptFile(byte[] file,String name)throws IOException;

}

package com.gobravery.wind.datacp.svc;

import java.io.IOException;

import javax.xml.ws.WebServiceClient;
@WebServiceClient(targetNamespace="http://impl.server.wind.gobravery.com",wsdlLocation="${app.ws.wind.url}/wind/services/uploadService?wsdl")
public interface UploadService {
	public void acceptFile(byte[] file,String name)throws IOException;
}

package com.gobravery.wind.ws;

import javax.xml.ws.WebServiceClient;

@WebServiceClient(wsdlLocation="${app.ws.csscis.url}/ebs/services/receiverService?wsdl",targetNamespace="http://service.dataex.oa.apps.css.com")
public interface ITestService {
	public void test(String sd);
	public String receive(String sd);
}

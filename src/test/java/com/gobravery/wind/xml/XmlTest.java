package com.gobravery.wind.xml;

import com.gobravery.wind.common.xml.ValidateXMLDTD;

public class XmlTest {
	@org.junit.Test  
	public void testValidate(){
		ValidateXMLDTD.test("cptables.xml");
	}
}

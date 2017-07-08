package cp.test.xml;

import cp.csscis.xml.ValidateXMLDTD;

public class XmlTest {
	@org.junit.Test  
	public void testValidate(){
		ValidateXMLDTD.test("cptables.xml");
	}
}

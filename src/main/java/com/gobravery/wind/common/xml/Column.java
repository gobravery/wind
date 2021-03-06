package com.gobravery.wind.common.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
	@XmlValue
	public String value;
	
	@XmlAttribute(name = "type")
	public String type;
	
	@XmlAttribute(name = "length")
	public Integer length;
	
	@XmlAttribute(name = "auto")
	public boolean auto=false;
	
	
}

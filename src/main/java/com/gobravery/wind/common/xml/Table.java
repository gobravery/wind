package com.gobravery.wind.common.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
	
	@XmlElement(name = "name")
	public String name;
	@XmlElement(name = "columns")
	public Columns columns;
	@XmlElement
	public String primarycol;
	@XmlElement
	public String cpkey;
}

package com.gobravery.wind.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XUser  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 234234241L;
	
	String username;
	
	String password ;
	public XUser(){
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

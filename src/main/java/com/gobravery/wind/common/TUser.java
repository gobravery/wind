package com.gobravery.wind.common;

import java.io.Serializable;

public class TUser  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 234234241L;
	
	String username;
	
	String password ;
	public TUser(){
		
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

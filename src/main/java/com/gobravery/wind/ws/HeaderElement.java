package com.gobravery.wind.ws;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

public class HeaderElement {
	String url;
	String cld;
	/**
	 * @param url 鍦板潃濡傦細www.csscis.com
	 * @param cld 浼佷笟浠ｇ爜濡傦細csscis
	 */
	public HeaderElement(String url,String cld){
		this.url=url;
		this.cld=cld;
	}
	public OMElement getHeader() {
		return getHeader("bank","root","1234556");
	}
	/**
	 * 鎷兼帴澶存枃浠�
	 *<security> 
	 * <username> root </username>
	 * <role> csscis </role> 
	 * <password> password </password>  
	 * </security> 
	 * @param rol 瑙掕壊缂� 鐮� :bank
	 * @param user 鐢ㄦ埛鍚�:root
	 * @param pwd 瀵嗙爜:123456
	 * @return
	 */
	public OMElement getHeader(String rol,String user,String pwd){
		OMFactory omFactory = OMAbstractFactory.getOMFactory();
		OMNamespace omNS=omFactory.createOMNamespace(url,cld);
		
		//OMElement head = omFactory.createOMElement("Security", omNS);
		OMElement token = omFactory.createOMElement("security", omNS);
		//head.addChild(token);
		OMElement role = omFactory.createOMElement("role", omNS);
		role.addChild(omFactory.createOMText(role, rol));
		token.addChild(role);
		///
		OMElement userName = omFactory.createOMElement("username", omNS);
		userName.addChild(omFactory.createOMText(userName, user));
		token.addChild(userName);
			
		OMElement password = omFactory.createOMElement("password", omNS);
		//password.addAttribute(omFactory.createOMAttribute("type", null, "passwordText"));
		password.addChild(omFactory.createOMText(password, pwd));
		token.addChild(password);
		System.out.println("-----------------"+token.toString());
		return token;
	}
}

package com.gobravery.wind.common.cp;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection getDMSConnection(int count) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url="jdbc:mysql://localhost:3306/mysql?user=root&password=root";
		 
		Connection con = DriverManager.getConnection(url);

		return con;
	}
	public static Connection getDMSConnection()throws Exception{
		return getDMSConnection(1);
	}
}

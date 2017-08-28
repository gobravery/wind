package com.gobravery.wind.cp;

import java.util.List;

public class SQLPkg {
	public static String getQuerySQL(String table,List<String> cols,String cpkey,String cpkeyVal){
		
		String sql="select "+listToString(cols)+" from "+table+" s where s."+cpkey+" > "+cpkeyVal;
		return sql;
	}
	public static String getQuerySQL(String table,List<String> cols,String cpkey,String cpkeyVal,int start,int count){
		
		String sql="select "+listToString(cols)+" from "+table+" s where s."+cpkey+" > '"+cpkeyVal+"' limit "+start+","+count;
		return sql;
	}
	public static String getCountSQL(String table,String cpkey,String cpkeyVal){
		
		String sql="select count(s."+cpkey+") from "+table+" s where s."+cpkey+" > '"+cpkeyVal+"'";
		return sql;
	}
	public static String getCpkeyValSQL(String table,String cpkey){
		String sql="select s.VAL from SYNCDATA s WHERE s.TAB='"+table+"' AND s.COL='"+cpkey+"'";
		return sql;
	}
	/**
	 * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
	 */
	public static String converToString(String[] ig) {
		String str = "";
		if (ig != null && ig.length > 0) {
			for (int i = 0; i < ig.length; i++) {
				str += ig[i] + ",";
			}
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * @Description:把list转换为一个用逗号分隔的字符串
	 */
	public static String listToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ",");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}
}

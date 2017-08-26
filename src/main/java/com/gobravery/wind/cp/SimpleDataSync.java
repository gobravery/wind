package com.gobravery.wind.cp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gobravery.wind.xml.Column;
import com.gobravery.wind.xml.Table;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SimpleDataSync extends AbstractDataSync{
	
	public boolean syncData(Table table) throws Exception{
		List<String> ls=new ArrayList<String>();
		String tableCpkey=cpkey;
		if(table.cpkey!=null){
			tableCpkey=table.cpkey;
		}
		for(Column col:table.columns.getColumns()){
			ls.add(col.value);
		}
		String cpkeyVal=getCpkeyVal(table.name,  tableCpkey);
		
		// 获得核心库连接
        Connection coreConnection = ConnectionFactory.getDMSConnection(4);  
        Statement coreStmt = coreConnection.createStatement();  
       // 为每个线程分配结果集
        String countsql=SQLPkg.getCountSQL(table.name, tableCpkey, cpkeyVal);
        ResultSet coreRs = coreStmt.executeQuery(countsql); 
        coreRs.next();  
       // 总共处理的数量
        long totalNum = coreRs.getLong(1);  
        // 线程数
        int ownerRecordNum = (int)Math.ceil((totalNum / (float)syncThreadNum));   
        for(int i=0; i < ownerRecordNum; i ++){
        	String querysql=SQLPkg.getQuerySQL(table.name, ls, tableCpkey, cpkeyVal,i*syncThreadNum,syncThreadNum);
        	coreRs = coreStmt.executeQuery(querysql); 
        	JSONArray ja=new JSONArray();
        	while (coreRs.next()) { 
        		for(Column col:table.columns.getColumns()){
        			JSONObject jo=new JSONObject();
        			jo.put(col.value, getValue(coreRs, col));
        			ja.add(jo);
        		}
        	}
        	//
        	String data=pkgData(ja);
        	sendData(data);
        }
        return true;
	}
	public String pkgData(JSONArray ja){
		return ja.toString();
	}

}

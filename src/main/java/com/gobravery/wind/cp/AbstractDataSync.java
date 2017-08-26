package com.gobravery.wind.cp;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.xml.sax.SAXException;

import com.gobravery.wind.common.Log4jUtils;
import com.gobravery.wind.common.SendPropertyiesUtils;
import com.gobravery.wind.common.SendResult;
import com.gobravery.wind.ctrl.WsFileUtils;
import com.gobravery.wind.xml.Column;
import com.gobravery.wind.xml.JaxbReadXml;
import com.gobravery.wind.xml.Table;
import com.gobravery.wind.xml.Tables;

import net.sf.json.JSONArray;

public abstract class AbstractDataSync {
	protected int syncThreadNum = 100; // ������
	protected String primarycol; // ������
	protected String cpkey; // ������
	public Tables getTables() throws JAXBException, SAXException,
			ParserConfigurationException {
		Tables tbs = JaxbReadXml.readStream(
				Tables.class,
				AbstractDataSync.class.getClassLoader().getResourceAsStream(
						"cptables.xml"));
		return tbs;
	}

	public abstract boolean syncData(Table table) throws Exception;

	public abstract String pkgData(JSONArray ja);

	public void syncStart(){
		Tables tb;
		try {
			tb = getTables();
			primarycol=tb.primarycol;
			cpkey=tb.cpkey;
			for(Table t:tb.getTables()){
				System.out.println("-----------"+t.name+"----------------");
				syncData(t);
			}
		} catch (Exception e) {
			System.out.println("�������ļ�ʧ��,����cptables.xml�ļ�!");
			e.printStackTrace();
			return;
		} 
	}

	public boolean sendData(String data) {
		SendPropertyiesUtils pu = new SendPropertyiesUtils();
		final String ip = pu.get("ip").toString();
		final int port = Integer.valueOf(pu.get("port").toString());
		WsFileUtils ft = new WsFileUtils();
		SendResult sr;
		try {
			sr = ft.send(data);
		} catch (AxisFault e) {
			sr = new SendResult();
			sr.setRemark(e.getLocalizedMessage());
			sr.setSuccess(false);
			e.printStackTrace();
		} catch (RemoteException e) {
			sr = new SendResult();
			sr.setRemark(e.getLocalizedMessage());
			sr.setSuccess(false);
			e.printStackTrace();
		}
		Log4jUtils.i(sr.toString());
		return true;
	}

	public String getCpkeyVal(String tablename, String cpkey)throws Exception {
		// ��ú��Ŀ�����
        Connection coreConnection = ConnectionFactory.getDMSConnection(4);  
        Statement coreStmt = coreConnection.createStatement();  
       // Ϊÿ���̷߳�������
        ResultSet coreRs = coreStmt.executeQuery(SQLPkg.getCpkeyValSQL(tablename,cpkey)); 
        coreRs.next();  
        String cpkeyVal = coreRs.getString(1);  
		
		return cpkeyVal;
	}

	public Object getValue(ResultSet rs, Column col) throws SQLException {
		if (col.type.equals("String")) {

			return rs.getString(col.value);
		} else {
			return rs.getObject(col.value);
		}
	}
}

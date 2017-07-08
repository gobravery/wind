package cp.csscis.cp;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;

import org.apache.axis2.AxisFault;
import org.xml.sax.SAXException;

import cp.csscis.common.Log4jUtils;
import cp.csscis.common.PropertyiesUtils;
import cp.csscis.common.SendResult;
import cp.csscis.webtest.FileTestUtils;
import cp.csscis.xml.Column;
import cp.csscis.xml.JaxbReadXml;
import cp.csscis.xml.Table;
import cp.csscis.xml.Tables;

public abstract class AbstractDataSync {
	protected int syncThreadNum = 100; // 数据量
	protected String primarycol; // 数据量
	protected String cpkey; // 数据量
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
			System.out.println("打开配置文件失败,请检查cptables.xml文件!");
			e.printStackTrace();
			return;
		} 
	}

	public boolean sendData(String data) {
		PropertyiesUtils pu = new PropertyiesUtils();
		final String ip = pu.get("ip").toString();
		final int port = Integer.valueOf(pu.get("port").toString());
		FileTestUtils ft = new FileTestUtils();
		SendResult sr;
		try {
			ft.init(ip, port);
			sr = ft.myname(data);
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
		// 获得核心库连接
        Connection coreConnection = ConnectionFactory.getDMSConnection(4);  
        Statement coreStmt = coreConnection.createStatement();  
       // 为每个线程分配结果集
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

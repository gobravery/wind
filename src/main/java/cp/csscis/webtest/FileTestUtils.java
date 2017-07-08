package cp.csscis.webtest;

import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;

import cp.csscis.common.JsonDataUtils;
import cp.csscis.common.Log4jUtils;
import cp.csscis.common.SendResult;
import cp.csscis.common.Utils;
import cp.csscis.webtest.FileWebServiceClient.UploadWithDataHandlerResponse;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class FileTestUtils {
	static RPCServiceClient serviceClient=null;
	public void init(String ip,int port) throws AxisFault,RemoteException {
		String url="http://127.0.0.1:8080/datacp/services/FileWebService";
		if(ip!=null && port>0){
			url="http://"+ip+":"+port+"/datacp/services/FileWebService";
		}
		EndpointReference targetEPR = new EndpointReference(url
				);
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
		options.setManageSession(true);//保持session
		//login();
		//say();
		//upload("d:\\pur_database\\pur20131101.dmp");
	}
	/**
	 *调用测试，无入参,反回值为String
	 *
	 */
	public void say() throws AxisFault {

		QName opGetWeather = new QName("http://webservice.csscis.cp",
				"say");
		Object[] opGetWeatherArgs = new Object[] {};
		Class[] returnTypes = new Class[] {String.class};
		Object[] response = serviceClient.invokeBlocking(opGetWeather,
				opGetWeatherArgs, returnTypes);

		if (response == null || response.length == 0) {
			System.out.println("Weather didn't initialize!");
		} else {
			String result = (String) response[0];
			System.out.println();
			System.out.println("Temperature               : " + result);
		}
	}
	public void saveMsgLog(SendResult sr){
		String f=">>>时间：%s,成功：%s,耗时：%s(S),发送长度：%s(Byte),接收长度：%s(Byte),备注:%s";
		String re=String.format(f, JsonDataUtils.toJson(sr.getCreateTime()),sr.isSuccess(),sr.getTime(),sr.getSendleng(),sr.getReciveleng(),sr.getRemark());
		//Log4jUtils.i(re);
	}
	public void saveFileLog(SendResult sr){
		String f=">>>时间：%s,成功：%s,耗时：%s(S),速度：%s(MB/S),文件大小：%s(MB),发送长度：%s(Byte),接收长度：%s(Byte)";
		String re=String.format(f, JsonDataUtils.toJson(sr.getCreateTime()),sr.isSuccess(),sr.getTime(),sr.getSpeed(),sr.getSize(),sr.getSendleng(),sr.getReciveleng(),sr.getRemark());
		Log4jUtils.i(re);
	}
	/**
	 *调用测试，入参为String,无反回值
	 *
	 */
	public SendResult myname(String msg) throws AxisFault {
		SendResult sr=new SendResult();
		long s=System.currentTimeMillis();
		QName opGetWeather = new QName("http://webservice.csscis.cp",
				"myname");
		String name=msg ;
		Object[] opGetWeatherArgs = new Object[] { name };
		Class[] returnTypes = new Class[] {Long.class};
		//serviceClient.invokeRobust(opGetWeather,opGetWeatherArgs);
		Object[] response = serviceClient.invokeBlocking(opGetWeather,
				opGetWeatherArgs, returnTypes);
		long e=System.currentTimeMillis();
		sr.setSendleng(name.getBytes().length);
		if (response == null || response.length == 0) {
			sr.setSuccess(false);
			sr.setReciveleng(-1);
		} else {
			Long result = (Long) response[0];
			System.out.println("发送长度: "+name.length()+",接收长度:" + result);
			sr.setReciveleng(Long.valueOf(result));
			sr.setSuccess(sr.getSendleng()==sr.getReciveleng());
		}
		double hs=(e-s)/1000d;
		sr.setTime(hs);
		saveMsgLog(sr);
		return sr;
	}
	/**
	 *登陆用于session保持测试
	 *
	 */
	public static void login() throws AxisFault {

		QName opGetWeather = new QName("http://webservice.csscis.cp",
				"login");
		Object[] opGetWeatherArgs = new Object[] { "asfasdf" };
		Class[] returnTypes = new Class[] {};
		serviceClient.invokeRobust(opGetWeather,
				opGetWeatherArgs);
	}
	/**
	 *文件传输 
	 * 
	 */
	public SendResult upload(String file) throws RemoteException  {
		SendResult sr=new SendResult();
		long s=System.currentTimeMillis();
		System.out.println("begin");
		FileWebServiceClient stub=new FileWebServiceClient();
		FileWebServiceClient.UploadWithDataHandler hd=new FileWebServiceClient.UploadWithDataHandler();
		FileDataSource fds=new FileDataSource(file);
		if(fds.getFile().exists() && fds.getFile().isFile()){
			long b=fds.getFile().length();//byte;
			double mb=b/1024d/1024d;
			DataHandler dateFile = new DataHandler(fds);  
			String name=fds.getName();
			hd.setArgs0(dateFile);
			hd.setArgs1(name);
			hd.setArgs2(b);
			UploadWithDataHandlerResponse response=stub.uploadWithDataHandler(hd);
			//System.out.println("DataHandler上传？"+hd.isArgs0Specified()+"   "+"名字有无命名："+hd.isArgs1Specified());
			long e=System.currentTimeMillis();
			double hs=(e-s)/1000d;
			sr.setTime(hs);
			sr.setSize(Utils.get2Double(mb));
			sr.setSpeed(Utils.get2Double(mb/hs));
			sr.setSendleng(b);
			sr.setReciveleng(response.local_return);
			sr.setSuccess(sr.getSendleng()==sr.getReciveleng());//成功
			System.out.println("传输耗时:"+sr.getTime()+"s"+",文件大小:"+sr.getSize()+"MB,速率:"+sr.getSpeed()+"MB/s");
			System.out.println("发送长度(byte)："+sr.getSendleng()+",接收长度(byte)："+(sr.getReciveleng())+"");
			System.out.println("end");
		}else{
			sr.setRemark("文件不存在,或者不是文件");
			saveFileLog(sr);
		}
		return sr;
	}
}

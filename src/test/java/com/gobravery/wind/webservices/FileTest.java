package com.gobravery.wind.webservices;

import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.gobravery.wind.common.Utils;
import com.gobravery.wind.webservices.FileWebServiceStub.UploadWithDataHandlerResponse;

public class FileTest {
	static RPCServiceClient serviceClient=null;
	public static void main(String[] args) throws AxisFault,RemoteException {
		EndpointReference targetEPR = new EndpointReference(
				"http://127.0.0.1:8080/mvc1/services/FileWebService");
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
		options.setManageSession(true);//保持session
		//login();
		//say();
		upload();
	}
	/**
	 *调用测试，无入参,反回值为String
	 *
	 */
	public static void say() throws AxisFault {

		QName opGetWeather = new QName("http://webservice.csscis.mvc1",
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
	/**
	 *调用测试，入参为String,无反回值
	 *
	 */
	public static void myname() throws AxisFault {

		QName opGetWeather = new QName("http://webservice.csscis.mvc1",
				"myname");
		String name="asfasdf" ;
		Object[] opGetWeatherArgs = new Object[] { name };
		Class[] returnTypes = new Class[] {};
		//serviceClient.invokeRobust(opGetWeather,opGetWeatherArgs);
		Object[] response = serviceClient.invokeBlocking(opGetWeather,
				opGetWeatherArgs, returnTypes);
		if (response == null || response.length == 0) {
			System.out.println("Weather didn't initialize!");
		} else {
			String result = (String) response[0];
			System.out.println();
			System.out.println("发送长度: "+name.length()+",接收长度:" + result);
		}
	}
	/**
	 *登陆用于session保持测试
	 *
	 */
	public static void login() throws AxisFault {

		QName opGetWeather = new QName("http://webservice.csscis.mvc1",
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
	public static void upload() throws RemoteException  {
		long s=System.currentTimeMillis();
		System.out.println("begin");
		FileWebServiceStub stub=new FileWebServiceStub();
		FileWebServiceStub.UploadWithDataHandler hd=new FileWebServiceStub.UploadWithDataHandler();
		FileDataSource fds=new FileDataSource("d:\\pur_database\\pur20131101.dmp");
		if(fds.getFile().exists() && fds.getFile().isFile()){
			long b=fds.getFile().length();//byte;
			double mb=b/1024d/1024d;
			DataHandler dateFile = new DataHandler(fds);  
			hd.setArgs0(dateFile);
			hd.setArgs1("fileUp");
			hd.setArgs2(b);
			UploadWithDataHandlerResponse response=stub.uploadWithDataHandler(hd);
			System.out.println("DataHandler上传？"+hd.isArgs0Specified()+"   "+"名字有无命名："+hd.isArgs1Specified());
			long e=System.currentTimeMillis();
			double hs=(e-s)/1000d;
			System.out.println("传输耗时:"+hs+"s"+",文件大小:"+Utils.get2Double(mb)+"MB,速率:"+Utils.get2Double(mb/hs)+"MB/s");
			System.out.println("发送长度(byte)："+b+",接收长度(byte)："+(response.local_return)+"");
			System.out.println("end");
		}else{
			System.out.println("文件不存在,或者不是文件");
		}
	}
}

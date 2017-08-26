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
		options.setManageSession(true);//����session
		//login();
		//say();
		upload();
	}
	/**
	 *���ò��ԣ������,����ֵΪString
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
	 *���ò��ԣ����ΪString,�޷���ֵ
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
			System.out.println("���ͳ���: "+name.length()+",���ճ���:" + result);
		}
	}
	/**
	 *��½����session���ֲ���
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
	 *�ļ����� 
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
			System.out.println("DataHandler�ϴ���"+hd.isArgs0Specified()+"   "+"��������������"+hd.isArgs1Specified());
			long e=System.currentTimeMillis();
			double hs=(e-s)/1000d;
			System.out.println("�����ʱ:"+hs+"s"+",�ļ���С:"+Utils.get2Double(mb)+"MB,����:"+Utils.get2Double(mb/hs)+"MB/s");
			System.out.println("���ͳ���(byte)��"+b+",���ճ���(byte)��"+(response.local_return)+"");
			System.out.println("end");
		}else{
			System.out.println("�ļ�������,���߲����ļ�");
		}
	}
}

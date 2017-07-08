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
		options.setManageSession(true);//����session
		//login();
		//say();
		//upload("d:\\pur_database\\pur20131101.dmp");
	}
	/**
	 *���ò��ԣ������,����ֵΪString
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
		String f=">>>ʱ�䣺%s,�ɹ���%s,��ʱ��%s(S),���ͳ��ȣ�%s(Byte),���ճ��ȣ�%s(Byte),��ע:%s";
		String re=String.format(f, JsonDataUtils.toJson(sr.getCreateTime()),sr.isSuccess(),sr.getTime(),sr.getSendleng(),sr.getReciveleng(),sr.getRemark());
		//Log4jUtils.i(re);
	}
	public void saveFileLog(SendResult sr){
		String f=">>>ʱ�䣺%s,�ɹ���%s,��ʱ��%s(S),�ٶȣ�%s(MB/S),�ļ���С��%s(MB),���ͳ��ȣ�%s(Byte),���ճ��ȣ�%s(Byte)";
		String re=String.format(f, JsonDataUtils.toJson(sr.getCreateTime()),sr.isSuccess(),sr.getTime(),sr.getSpeed(),sr.getSize(),sr.getSendleng(),sr.getReciveleng(),sr.getRemark());
		Log4jUtils.i(re);
	}
	/**
	 *���ò��ԣ����ΪString,�޷���ֵ
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
			System.out.println("���ͳ���: "+name.length()+",���ճ���:" + result);
			sr.setReciveleng(Long.valueOf(result));
			sr.setSuccess(sr.getSendleng()==sr.getReciveleng());
		}
		double hs=(e-s)/1000d;
		sr.setTime(hs);
		saveMsgLog(sr);
		return sr;
	}
	/**
	 *��½����session���ֲ���
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
	 *�ļ����� 
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
			//System.out.println("DataHandler�ϴ���"+hd.isArgs0Specified()+"   "+"��������������"+hd.isArgs1Specified());
			long e=System.currentTimeMillis();
			double hs=(e-s)/1000d;
			sr.setTime(hs);
			sr.setSize(Utils.get2Double(mb));
			sr.setSpeed(Utils.get2Double(mb/hs));
			sr.setSendleng(b);
			sr.setReciveleng(response.local_return);
			sr.setSuccess(sr.getSendleng()==sr.getReciveleng());//�ɹ�
			System.out.println("�����ʱ:"+sr.getTime()+"s"+",�ļ���С:"+sr.getSize()+"MB,����:"+sr.getSpeed()+"MB/s");
			System.out.println("���ͳ���(byte)��"+sr.getSendleng()+",���ճ���(byte)��"+(sr.getReciveleng())+"");
			System.out.println("end");
		}else{
			sr.setRemark("�ļ�������,���߲����ļ�");
			saveFileLog(sr);
		}
		return sr;
	}
}

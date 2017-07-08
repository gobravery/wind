package cp.csscis.webservice.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;

import cp.csscis.common.PropertyiesUtils;
import cp.csscis.webservice.FileWebservice;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceContext;
import org.springframework.stereotype.Component;

@Component("fileWebService")
public class FileWebserviceImpl implements FileWebservice{
	public String say(){
		System.out.println("hello my is fileWebservice!my name:"+getSessionValue("login"));
		return "hello my is fileWebservice";
	}
	public long myname(String name){
		//开始session，存储
		System.out.println("my name is :"+name);
		return name.getBytes().length;
	}
	public void login(String name){
		//开始session，存储
		MessageContext mc = MessageContext.getCurrentMessageContext();  
        ServiceContext sc = mc.getServiceContext();  
        sc.setProperty("login", name);      
		System.out.println("my name is :"+name);
	}
	public Object getSessionValue(String key){
		MessageContext mc = MessageContext.getCurrentMessageContext();  
        ServiceContext sc = mc.getServiceContext();  
        return  sc.getProperty(key);      
	}
	 // 使用byte[]类型参数上传二进制文件  
    public boolean uploadWithByte(byte[] file, String filename) {  
        FileOutputStream fos = null;  
        try {  
            fos = new FileOutputStream(filename);  
            fos.write(file);  
            fos.close();  
        } catch (Exception e) {  
            return false;  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (Exception e) {  
                }  
            }  
        }  
        return true;  
    }  
  
    private void writeInputStreamToFile(InputStream is, OutputStream os)  
            throws Exception {  
        int n = 0;  
        byte[] buffer = new byte[8192];  
        while ((n = is.read(buffer)) > 0) {  
            os.write(buffer, 0, n);  
        }  
    }  
  
    // 使用DataHandler类型参数上传文件  
    public long uploadWithDataHandler(DataHandler file, String filename,long size) {
  
        FileOutputStream fos = null;  
        PropertyiesUtils pu=new PropertyiesUtils();
        String saveFilePath=pu.get("saveFilePath").toString();
        String dir=saveFilePath;//"d:"+File.separator+"send_test";
        try {
        	File f=new File(dir);
        	if(!f.exists()){
        		f.mkdirs();
        	}
        	long temp=System.currentTimeMillis();
        	String filepath=dir+File.separator+temp+"_"+filename;
            fos = new FileOutputStream(filepath);  
            // 可通过DataHandler类的getInputStream方法读取上传数据  
            writeInputStreamToFile(file.getInputStream(), fos);  
            fos.close();  
            File s=new File(filepath);  
            System.out.println("接收："+s.length()+",发送:"+size);
            return s.length();
        } catch (Exception e) {
        	e.printStackTrace();
            return 0l;  
        } finally {
            if (fos != null) {
                try {  
                    fos.close();  
                } catch (Exception e) {  
                	e.printStackTrace();
                }  
            }  
        }  
    }
}

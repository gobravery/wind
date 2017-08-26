package com.gobravery.wind.ctrl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gobravery.wind.common.Result;
import com.gobravery.wind.common.SendPropertyiesUtils;
import com.gobravery.wind.common.SendResult;
import com.gobravery.wind.common.Utils;
import com.gobravery.wind.cp.SimpleDataSync;
@Scope("prototype")
@Controller
@RequestMapping("/send")
public class SendTest {
	@Autowired
	public Utils u;
	int i=0;
	@RequestMapping(value = "/index")
	// ��������ǰ̨��login����
	private ModelAndView index(HttpServletResponse re, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("index");
		SendPropertyiesUtils pu=new SendPropertyiesUtils();
		mv.addObject("ip", pu.get("ip"));
		mv.addObject("port", pu.get("port"));
		mv.addObject("sendMsg", pu.get("sendMsg"));
		mv.addObject("sendMsgCount", pu.get("sendMsgCount"));
		mv.addObject("sendFile", pu.get("sendFile"));
		mv.addObject("sendFileCount", pu.get("sendFileCount"));
		return mv;

	}
	@RequestMapping(value = "/msgResult")
	// ��������ǰ̨��login����
	private @ResponseBody List<SendResult> msgResult(HttpServletResponse re, HttpServletRequest req) {
		List<SendResult> msgList = getMsgResult(req);
		System.out.println("������"+i);
		i++;
		return msgList;
	}
	private List<SendResult> getMsgResult(HttpServletRequest req) {
		ServletContext sc=req.getSession().getServletContext();
		List<SendResult> msgList;
		Object msg=sc.getAttribute("msgresult");
		if(msg==null){
			msgList=new ArrayList<SendResult>();
			sc.setAttribute("msgresult", msgList);
		}else{
			msgList=(List<SendResult>)msg;
		}
		return msgList;
	}
	@RequestMapping(value = "/fileResult")
	// ��������ǰ̨��login����
	private @ResponseBody List<SendResult> fileResult(HttpServletResponse re, HttpServletRequest req) {
		List<SendResult> fileList = getFileResult(req);
		return fileList;
	}
	private  List<SendResult>  getFileResult(HttpServletRequest req) {
		ServletContext sc=req.getSession().getServletContext();
		List<SendResult> fileList;
		Object file=sc.getAttribute("fileresult");
		if(file==null){
			fileList=new ArrayList<SendResult>();
			// Collections.synchronizedCollection(fileList);
			sc.setAttribute("fileresult", fileList);
		}else{
			fileList=(List<SendResult>)file;
		}
		return fileList;
	}
	@RequestMapping("/sve")
	// ��������ǰ̨��login����
	public @ResponseBody Result sendsve(
			@RequestParam(value = "ip", required = false) String ip,
			@RequestParam(value = "port", required = false) int port,
			HttpServletResponse re, HttpServletRequest req

	)  throws AxisFault,RemoteException {
		SendPropertyiesUtils pu=new SendPropertyiesUtils();
		pu.set("ip", ip);
		pu.set("port", port+"");
		Result r=new Result(true);
		r.setMessage("�ɹ�!");
		return r;
	}
	@RequestMapping("/str")
	// ��������ǰ̨��login����
	public @ResponseBody Result sendstr(
			@RequestParam(value = "sendMsg", required = false) String msg,
			@RequestParam(value = "sendMsgCount", required = false) int count,
			HttpServletResponse re, HttpServletRequest req

	)  {
		SendPropertyiesUtils pu=new SendPropertyiesUtils();
		final String ip=pu.get("ip").toString();
		final int port=Integer.valueOf(pu.get("port").toString());
		final String sendMsg=msg;
		pu.set("sendMsg", sendMsg);
		pu.set("sendMsgCount", count+"");
		ScheduledThreadPoolExecutor ep=new ScheduledThreadPoolExecutor(2);
		final List<SendResult> msgList = getMsgResult(req);
		do{
			ep.submit(new Callable<Integer>() {
				public Integer call() {
					SimpleDataSync c=new SimpleDataSync();
					c.syncStart();
					/*FileTestUtils ft=new FileTestUtils();
					SendResult sr;
					try {
						ft.init(ip, port);
						sr = ft.myname(sendMsg);
					} catch (AxisFault e) {
						sr=new SendResult();
						sr.setRemark(e.getLocalizedMessage());
						sr.setSuccess(false);
						e.printStackTrace();
					} catch (RemoteException e) {
						sr=new SendResult();
						sr.setRemark(e.getLocalizedMessage());
						sr.setSuccess(false);
						e.printStackTrace();
					}
					msgList.add(sr);*/
					return 1;
				}
			});
			count--;
		}while(count>0);
		Result r=new Result(true);
		r.setMessage("��ʼ");
		return r;
	}

	@RequestMapping(value = "/file")
	// ��������ǰ̨��login���� @RequestBody
	public @ResponseBody Result sendfile(
			@RequestParam(value = "sendFile", required = false) String sendFile,
			@RequestParam(value = "sendFileCount", required = false) Integer sendFileCount,
			HttpServletResponse re, HttpServletRequest req){
		SendPropertyiesUtils pu=new SendPropertyiesUtils();
		final List<SendResult> fileList = getFileResult(req);
		final String fileAdd=sendFile;
		final String ip=pu.get("ip").toString();
		final int port=Integer.valueOf(pu.get("port").toString());
		pu.set("sendFile", sendFile);
		pu.set("sendFileCount", sendFileCount+"");
		ScheduledThreadPoolExecutor ep=new ScheduledThreadPoolExecutor(2);
		do{
			ep.submit(new Callable<Integer>() {
				public Integer call() {
					
					WsFileUtils ft=new WsFileUtils();
					SendResult sr;
					try {
						sr = ft.upload(fileAdd);
					} catch (Exception e) {
						sr=new SendResult();
						sr.setRemark(e.getLocalizedMessage());
						e.printStackTrace();
					}
					fileList.add(sr);
					return null;
				}
			});
			sendFileCount--;
		}while(sendFileCount>0);
		Result r=new Result(true);
		r.setMessage("��ʼ");
		return r;
	}

}

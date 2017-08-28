package com.gobravery.wind.common;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SendResult {
	
	Date createTime=new Date();
	boolean success=false;
	double time=0d;//耗时,s
	double speed=0d;//速度,MB/s
	double size=0d;//总大小,MB
	long sendleng=0l;//发送长度,(byte)
	long reciveleng=0l;//接收长度,(byte)
	String remark="";//备注
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public long getSendleng() {
		return sendleng;
	}
	public void setSendleng(long sendleng) {
		this.sendleng = sendleng;
	}
	public long getReciveleng() {
		return reciveleng;
	}
	public void setReciveleng(long reciveleng) {
		this.reciveleng = reciveleng;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String toString(){
		String html="";
		html+="时间："+createTime+",";
		html+="成功："+success+",";
		html+="耗时："+time+"(s),";
		html+="速度："+speed+"(MB/S),";
		html+="文件大小："+size+"(MB),";
		html+="发送长度："+sendleng+"(Byte),";
		html+="接收长度："+reciveleng+"(Byte),";
		html+="备注："+remark+".";
		return html;
	}
}

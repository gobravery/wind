package cp.csscis.common;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SendResult {
	
	Date createTime=new Date();
	boolean success=false;
	double time=0d;//��ʱ,s
	double speed=0d;//�ٶ�,MB/s
	double size=0d;//�ܴ�С,MB
	long sendleng=0l;//���ͳ���,(byte)
	long reciveleng=0l;//���ճ���,(byte)
	String remark="";//��ע
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
		html+="ʱ�䣺"+createTime+",";
		html+="�ɹ���"+success+",";
		html+="��ʱ��"+time+"(s),";
		html+="�ٶȣ�"+speed+"(MB/S),";
		html+="�ļ���С��"+size+"(MB),";
		html+="���ͳ��ȣ�"+sendleng+"(Byte),";
		html+="���ճ��ȣ�"+reciveleng+"(Byte),";
		html+="��ע��"+remark+".";
		return html;
	}
}

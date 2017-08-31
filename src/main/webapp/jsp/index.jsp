<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
</head>
<body style="font-size: 12px;padding: 15px;">
	<h2 align="center">web service test!</h2>
	<%
		String path = request.getContextPath();
	%>
	<script type="text/javascript"
		src="<%=path%>/plugins/jquery/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#test").click(function() {
						sendFile();
					});
					$("#sve").click(function() {
						sendSve();
					});
					$("#str").click(function() {
						sendStr();
					});
					$("#msgquery").click(function() {
						showSendStr();
					});
					$("#filequery").click(function() {
						showSendFile();
					});
					function sendFile() {
						var mydata = {sendFile:$('#sendFile').val()
								,sendFileCount:$('#sendFileCount').val()};
						$.ajax({
							type: "GET",
							//contentType : 'application/json',
							contentType:"application/x-www-form-urlencoded; charset=utf-8",
							url : '<%=path%>/send/file',
							//processData : false,
							dataType : 'json',  
							cache:false,
							data : mydata,
							success : function(data) {
								alert("ok!:" + data.message);
							},
							error : function() {
								alert('Err...');
							}
						});
					}
					function sendSve() {
						var mydata = {ip:$('#ip').val()
								,port:$('#port').val()};
						$.ajax({
							type: "GET",
							//contentType : 'application/json',
							contentType:"application/x-www-form-urlencoded; charset=utf-8",
							url : '<%=path%>/send/sve',
							//processData : false,
							dataType : 'json',  
							cache:false,
							data : mydata,
							success : function(data) {
								alert("ok!:" + data.message);
							},
							error : function() {
								alert('Err...');
							}
						});
					}
					function sendStr() {
						var mydata = {sendMsg:$('#sendMsg').val()
								,sendMsgCount:$('#sendMsgCount').val()};
						$.ajax({
							type: "POST",
							//contentType : 'application/json',
							contentType:"application/x-www-form-urlencoded; charset=utf-8",
							url : '<%=path%>/send/str',
							processData : true,
							cache:false,
							dataType : 'json',  
							data : mydata,
							success : function(data) {
								alert("ok!:" + data.message);
							},
							error : function() {
								alert('Err...');
							}
						});
					}
					//
					function showSendFile() {
						$.ajax({
							type: "GET",
							//contentType : 'application/json',
							contentType:"application/x-www-form-urlencoded; charset=utf-8",
							url : '<%=path%>/send/fileResult',
							//processData : false,
							cache:false,
							dataType : 'json',  
							data : {},
							success : function(data) {
								var html="";
								for(i=0;i<data.length;i++){
									var d=data[i];
									html+="序号："+i+",";
									html+="时间："+d.createTime+",";
									html+="成功："+d.success+",";
									html+="耗时："+d.time+"(s),";
									html+="速度："+d.speed+"(MB/S),";
									html+="文件大小："+d.size+"(MB),";
									html+="发送长度："+d.sendleng+"(Byte),";
									html+="接收长度："+d.reciveleng+"(Byte),";
									html+="备注："+d.remark+",";
									html+="<br>";
								}
								$('#fileresult').html(html);
							},
							error : function() {
								$('#fileresult').html("无测试结果");
							}
						});
					}
					function showSendStr() {
						$.ajax({
							type: "post",
							//contentType : 'application/json',
							contentType:"application/x-www-form-urlencoded; charset=utf-8",
							url : '<%=path%>/send/msgResult',
							//processData : false,
							dataType : 'json',  
							///cache:true,
							data : {},
							success : function(data) {
								var html="";
								for(i=0;i<data.length;i++){
									var d=data[i];
									html+="序号："+i+",";
									html+="时间："+d.createTime+",";
									html+="成功："+d.success+",";
									html+="耗时："+d.time+"(s),";
									html+="发送长度："+d.sendleng+"(Byte),";
									html+="接收长度："+d.reciveleng+"(Byte),";
									html+="备注："+d.remark+",";
									html+="<br>";
								}
								$('#msgresult').html(html);
							},
							error : function() {
								$('#msgresult').html("无测试结果");
							}
						});
					}
					//window.setInterval(showSendFile, 30000); 
					//window.setInterval(showSendStr, 30000); 
					//
				});
	</script>
	<form action="<%=path%>/send/sve">
		接口服务器地址：<input id="ip" name="ip" type="text" value="${ip}"></input>
		接口服务器端口：<input id="port" name="port" type="text" value="${port}"></input>
		<input type="button" value="修改" id="sve">（测试结果见：d:/mvc1_web_test.log）
	</form>
	<hr>
	<div style="height: 50%">
		<div style="float: left;width: 100%;">
			<form action="<%=path%>/send/str">
				发送信息：<input id="sendMsg" name="sendMsg" type="text"
					value="${sendMsg}"></input><br> 发送次数：<input id="sendMsgCount"
					name="sendMsgCount" type="text" value="${sendMsgCount}"></input><br>
				<input type="button" value="字符发送测试" id="str"><input type="button" id="msgquery" value="查询结果" />
			</form>
		</div>
		<div style="float: left;width: 100%;height: 150px;max-height: 150px;overflow: scroll;">
			<fieldset>
				<legend>字符发送测试结果</legend>
				<div id="msgresult">开始：</div>
			</fieldset>
		</div>
	</div>
	<div style="height: 50%">
		<div style="float: left;width: 100%;">
			<!-- accept-charset='UTF-8' enctype='application/x-www-form-urlencoded' -->
			<form action="<%=path%>/send/file" method="post">
				文件地址：<input id="sendFile" name="sendFile" type="text"
					value="${sendFile}"></input>服务器测试文件所在地址<br> 发送次数：<input
					id="sendFileCount" name="sendFileCount" type="text"
					value="${sendFileCount}"></input><br> <input type="button"
					id="test" value="文件发送测试" /><input type="button" id="filequery" value="查询结果" />
			</form>
		</div>
		<div style="float: left;width: 100%;height: 150px;max-height:150px;overflow: scroll;">
			<fieldset style="">
				<legend>文件发送测试结果(文件接收存取目录d:\send_test)</legend>
				<div id="fileresult">开始：</div>
			</fieldset>
		</div>
	</div>
	<font style="color: red;">注：如使用eclipse测试程序需要关闭自动部署，即将“Auto reloading enabled”勾选去掉。否则将会导致系统自动重启!</font>
</body>
</html>

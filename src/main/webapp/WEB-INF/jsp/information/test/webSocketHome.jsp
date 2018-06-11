<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ip =request.getServerName() ;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
var websocketonline;//websocke对象
var userCount = 0;	//在线总数
function online(){
	if (window.WebSocket) {
		websocketonline = new WebSocket(encodeURI('ws://${ip}:8887'));
		websocketonline.onopen = function() {
			websocketonline.send('1000000');//连接成功
		};
		websocketonline.onerror = function() {
			//连接失败
		};
		websocketonline.onclose = function() {
			//连接断开
		};
		//消息接收
		websocketonline.onmessage = function(message) {
			var message = JSON.parse(message.data);
			//alert(message.type);
			var s = document.getElementById("test").innerHTML;
			var b = message.type;
			var dfip = message.dfip;
			document.getElementById("test").innerHTML=s+'<div>'+dfip+'：'+b+'</div>';
		};
	}
}
online();

function send(){
	
	var content = document.getElementById("content").value;
	if(content=="" || content==null){
		alert("不能为空");
		return;
	}
	$.ajax({
        type: "GET",
        url: 'http://192.168.2.138:8080/zjdemo/wb/push/'+content,
        success: function (data) {
        	
        	var message = JSON.parse(message.data);
			
			var s = document.getElementById("test").innerHTML;
			var b = content;
			document.getElementById("test").innerHTML=s+'<div>我说：'+b+'</div>';
        }
    })
}

</script>
</head>
<body id="test">
Hello <%=ip %>
<div>在线聊天</div>
	<textarea id="content" rows="3" cols="2" style="width:200px;">
	</textarea>
	<button value="发送" onclick="send();">发送</button>
	

<br><br>
</body>
</html>

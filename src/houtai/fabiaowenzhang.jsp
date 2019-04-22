<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page language="java" import="bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script language="javascript">
	function validateFabiao() {
		var Title = document.frmFabiao.title.value;
		var Desc = document.frmFabiao.desc.value;
		var Content = document.frmFabiao.content1.value;
		var Type = document.frmFabiao.type.value;

		if ((Title == "请输入文章标题") || (Title == "")) {
			alert("请输入文章标题!");
			return false;
		}
		if ((Desc == "请输入文章描述") || (Desc == "")) {
			alert("请输入文章描述!");
			return false;
		}
		if ((Content == "请输入文章内容") || (Content == "")) {
			alert("请输入文章内容!");
			return false;
		}
		if ((Type == "请输入文章类型") || (Type == "")) {
			alert("请输入文章类型!");
			return false;
		}
		
		var strContent = document.getElementById("text1").value;
	    strContent = strContent.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome
	    strContent = strContent.replace(/\n/g, '<br/>'); //IE7-8
	    strContent = strContent.replace(/\s/g, ' '); //空格处理
	    /* alert("转换之后的html代码为\r\n"+strContent); */
	    document.getElementById("text2").innerHTML = strContent;
	}
</script>

<title>发表新博客</title>
</head>
<body>
	<center>
		<h1>My Blog</h1>
		<div class="login-01">
			<form action="${pageContext.request.contextPath}/fabiao" method="post"
				name="frmFabiao">



				<ul>

					<li class="first"><a href="#" class=" icon title"></a><input
						type="text" class="text" name="title" value="请输入文章标题"
						onfocus="if(this.value == '请输入文章标题') this.value =''">
						<div class="clear"></div></li>
					<li class="first"><a href="#" class=" icon desc"></a><input
						type="text" class="text" name="desc" value="请输入文章描述"
						onfocus="if(this.value == '请输入文章描述') this.value =''">
						<div class="clear"></div></li>
					<!-- <li class="first">
					<a href="#" class=" icon type"></a><input type="text" class="text" value="Type" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入文章的类型';}" >
					<div class="clear"></div>
				</li> -->
				<li class="third">
									<select class="type" value="Type">
										<option value="">请选择文章的类型</option>
										<option value="">科学</option>
										<option value="">娱乐</option>
										<option value="">计算机</option>
									</select>
					<div class="clear"></div>
				</li>
					<li class="second">
					<a href="#" class=" icon content"></a>
					<textarea id = "text1" name = "content1" value="请输入文章内容"
							 onFocus="this.value = '';">contents</textarea>
						<div class="clear"></div>
					<textarea rows="40" cols="90" style="display:none;" name="content" id="text2"></textarea>	
					</li>

				</ul>

				<input type="submit" onclick="return validateFabiao()"
					value="Submit">




				<div class="clear"></div>
			</form>
		</div>
		<!--start-copyright-->
		<div class="copy-right">
			<div class="wrap">
				<p>
					Copyright &copy; 2018.Company name All rights reserved.<a
						target="_blank" href="">博客</a>
				</p>
			</div>
		</div>
	</center>
	<!--//end-copyright-->

	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/retina-1.1.0.min.js"></script>
	<script src="assets/js/scripts.js"></script>
</body>
</html>
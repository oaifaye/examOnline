<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/backend_menu.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/backend_menu.js"></script>

</head>

<body>
	<div id="cleft_box">
		<div class="cleft_top"></div>
		<div class="cleft_foot">
			<ul>
				<li><a>用户管理</a>
					<ul class="childnav">
						<li class="liTop">
							<a>用户管理管理</a>
						</li>
						<li>
							<a>11111111</a>
						</li>
						<li>
							<a>11111111</a>
						</li>
					</ul>
				</li>
				<li><a>学员管理</a>
					<ul class="childnav">
						<li class="liTop"><a>用户管理管理</a>
						</li>
						<li><a>11111111</a>
						</li>
						<li><a>11111111</a>
						</li>
					</ul>
				</li>
				<li><a>基础信息</a>
					<ul class="childnav">
						<li class="liTop">
							<a href="<%=basePath%>initBaseInfo.action">基础信息管理</a>
						</li>
					</ul>
				</li>
				<li><a>试题管理</a>
					<ul class="childnav">
						<li class="liTop">
							<a href="<%=basePath%>listQuestion.action?gradeLevel=0">小学试题管理</a>
						</li>
						<li>
							<a href="<%=basePath%>listQuestion.action?gradeLevel=1">初中试题管理</a>
						</li>
						<li>
							<a href="<%=basePath%>listQuestion.action?gradeLevel=2">高中试题管理</a>
						</li>
						<li class="liTop">
							<a href="<%=basePath%>listPaper.action?gradeLevel=0">小学试卷管理</a>
						</li>
						<li>
							<a href="<%=basePath%>listPaper.action?gradeLevel=1">初中试卷管理</a>
						</li>
						<li>
							<a href="<%=basePath%>listPaper.action?gradeLevel=2">高中试卷管理</a>
						</li>
					</ul>
				</li>

			</ul>
		</div>
	</div>
</body>
</html>

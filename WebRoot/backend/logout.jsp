<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("manager",null);			
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>logout</title>
		<script type="text/javascript">
			top.location.href="<%=basePath%>backend/login.jsp";
		</script>
	</head>
	<body>
		正在退出系统...
	</body>
</html>

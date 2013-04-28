<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <style type="text/css">
  	.text{
  		color:#888888;
  	}
  	.text:hover{
  		color:#888888;
  		text-decoration: none;
  		cursor:default;
  	}
  </style>
  
  <script type="text/javascript" src="js/jquery.js" ></script>
	<script type="text/javascript">
	$(function(){
		//获取url参数
		var str=window.location.href;
		//截取最后的“/”与"."之间的字符串作为selected的id
		var actionName=str.substring(str.lastIndexOf('/')+1,str.lastIndexOf('.action'));
		$("#firstPage").click(function(){
			window.location.href=actionName+".action?isDelete=1&gradeLevel="+$("#gradeLevel").val();
		});
		
		$("#frontPage").click(function(){
			$currentPage=parseInt($("#currentPage").val())-1;
			window.location.href=actionName+".action?isDelete=1&gradeLevel="+$("#gradeLevel").val()+"&currentPage="+$currentPage;
		});
		
		$("#nextPage").click(function(){
			//alert($("#currentPage").val());
			$currentPage=parseInt($("#currentPage").val())+1;
			window.location.href=actionName+".action?isDelete=1&gradeLevel="+$("#gradeLevel").val()+"&currentPage="+$currentPage;
		});
		
		$("#lastPage").click(function(){
			window.location.href=actionName+".action?isDelete=1&gradeLevel="+$("#gradeLevel").val()+"&currentPage="+$("#totalPage").val();
		});
	});
	</script>
  </head>
  
  <body>
	 共<s:property value="pageBean.allRow"/>条 &nbsp;&nbsp;第<s:property value="currentPage"/>页&nbsp;&nbsp;
	 <s:if test="currentPage==1">
	 	<a class="text">首页</a>&nbsp;&nbsp;
	 	<a class="text">上一页</a>
	 </s:if>
	 <s:else>
	 	<a id="firstPage">首页</a>&nbsp;&nbsp;
	 	<a id="frontPage">上一页</a>
	 </s:else>
	 &nbsp;&nbsp;
	 <s:if test="currentPage==pageBean.totalPage">
	 	<a class="text">下一页</a>&nbsp;&nbsp;
	 	<a class="text">尾页</a>
	 </s:if>
	 <s:else>
	 	<a id="nextPage">下一页</a>&nbsp;&nbsp;
		<a id="lastPage">尾页</a>
	 </s:else>
	 &nbsp;&nbsp;
	 <s:hidden id="currentPage" value="%{currentPage}" ></s:hidden> 
	 <s:hidden id="totalPage" value="%{pageBean.totalPage}" ></s:hidden>
	 <s:hidden id="gradeLevel" value="%{gradeLevel}" ></s:hidden>
  </body>
</html>

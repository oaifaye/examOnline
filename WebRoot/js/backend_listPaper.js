$(function(){
	$("#addPaper").click(function(){
		window.location.href="initPaper.action?gradeLevel="+$("#gradeLevel").val();
	});
	
	$(".trContent").dblclick(function(){
		$paperId=$(this).find("input").val();
		window.location.href="editPaper.action?paperId="+$paperId;
	});
	
	$(".release").click(function(){
		var info =$(this).find("a").text();
		if(confirm("是否确定"+info+"此试卷?")){
			var $paperId=$(this).parent().find("input").val();
			window.location.href="releasePaper.action?paperId="+$paperId+"&currentPage="+parseInt($("#currentPage").val());
		}else{
			return false;
		}
	});
	
});
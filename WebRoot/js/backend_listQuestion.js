$(function(){
	$("#addQuestion").click(function(){
		window.location.href="initQuestion.action?gradeLevel="+$("#gradeLevel").val();
	});
	
	$(".trContent").dblclick(function(){
		$questionId=$(this).find("input").val();
		window.location.href="editQuestion.action?questionId="+$questionId;
	});
	
});

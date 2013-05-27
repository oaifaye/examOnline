var onbeforeunloadFlog=true;
$(function() {
	//初始化页面，使当前试题的索引被选中
	$(".index a").each(function(){
		if(parseInt($(this).text())-1==parseInt($("#currentIndex").val())){
			$(this).addClass("indexChecked");
		}
	});

	$("#previousQuestion").click(function() {
//		window.questionControl.method = "post";
//		window.questionControl.action = "previousQuestionFrontend.action";
//		window.questionControl.submit();
		$("#questionControl").attr("action","previousQuestionFrontend.action")//更改属性
	    .submit();//提交
	});

	$("#nextQuestion").click(function() {
//		window.questionControl.method = "post";
//		window.questionControl.action = "nextQuestionFrontend.action";
//		window.questionControl.submit();
		$("#questionControl").attr("action","nextQuestionFrontend.action")//更改属性
	    .submit();//提交
	});
	
	$(".indexButton").click(function() {
		//请求的试题索引与本题相符时  不跳转
		if(parseInt($(this).text())-1!=parseInt($("#currentIndex").val())){
			//把请求的试题索引赋给表单中的hidden，以便post提交
			$("#needIndex").val(parseInt($(this).text())-1);
//			window.questionControl.method = "post";
//			window.questionControl.action = "gainQuestionByIndexFrontend.action";
//			window.questionControl.submit();
			$("#questionControl").attr("action","gainQuestionByIndexFrontend.action")//更改属性
		    .submit();//提交
		}
	});
	

//	function gettempAnswer() {
//		var tempAnswer = "";
//		$("input[name=tempAnswer]").each(function() {
//			if ($(this).attr("checked") == true) {
//				tempAnswer += $(this).val();
//			}
//		});
//		return tempAnswer;
//	}

	// 根据单选或是多选，控制答案个数
	$("input[name='tempAnswer']").each(function() {
		$(this).click(function() {
			if ($("#questionType").val() == '0') {
				if ($(this).attr('checked')) {
					$("input[name='tempAnswer']").removeAttr('checked');
					$(this).attr('checked', 'checked');
				}
			}
		});
	});

	// 试题题号
	$("#questionIndex").text(parseInt($("#currentIndex").val()) + 1 + ". ");

	//试卷提交按钮
	$("#submitPaper").click(function(){
		//window.location.href="markingFrontend.action";
//		window.questionControl.method = "post";
//		window.questionControl.action = "markingFrontend.action";
//		window.questionControl.submit();
		$("#questionControl").attr("action","markingFrontend.action")//更改属性
	    .submit();//提交
	});

	//退出按钮
	$("#quitButton").click(function(){
		window.location.href="quitPaperFrontend.action";
	});
	
	
	
	// 禁用键盘刷新
//	$(document).keydown(function(e) {
//		// alert(e.keyCode)
//		var keycode = (e.keyCode) || (e.which) || (e.charCode);
//		if (keycode == 116) {
//			return false;
//		}
//	});
	
//	//页面刷新，默认提交
//	window.onbeforeunload = function() {
//		
//		if (onbeforeunloadFlog) {
////			alert(2)
////			window.questionControl.method = "post";
////			window.questionControl.action = document.location.href;
////			window.questionControl.submit();
////			alert(22)
//		}
//	};
});

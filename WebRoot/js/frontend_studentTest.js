var onbeforeunloadFlog=true;
$(function() {
	// 为点击时绕过onbeforeunload，只执行click而设置的变量
	 

	$("#previousQuestion").click(function() {
		//onbeforeunloadFlog=false;
		window.questionControl.method = "post";
		window.questionControl.action = "previousQuestionFrontend.action";
		window.questionControl.submit();
	});

	$("#nextQuestion").click(function() {
		//onbeforeunloadFlog=false;
		window.questionControl.method = "post";
		window.questionControl.action = "nextQuestionFrontend.action";
		window.questionControl.submit();
	});

	function gettempAnswer() {
		var tempAnswer = "";
		$("input[name=tempAnswer]").each(function() {
			if ($(this).attr("checked") == true) {
				tempAnswer += $(this).val();
			}
		});
		return tempAnswer;
	}

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
		window.questionControl.method = "post";
		window.questionControl.action = "markingFrontend.action";
		window.questionControl.submit();
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

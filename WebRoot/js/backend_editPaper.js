$(function() {
	if ($("#id").val() == "") {
		//添加试卷时，初始化调用
		$(".itemType").attr("checked", "");
		$(".itemType").siblings().each(function() {
			$(this).val("");
			$(this).attr("disabled", "disabled");
		});
	}else{
		//修改试卷时，初始化调用
		if($("#singleType").val()=="0"){
			$("#single").attr('checked','checked');
		}
		if($("#doubleType").val()=="1"){
			$("#double").attr('checked','checked');
		}
		if($("#subjectiveType").val()=="2"){
			$("#subjective").attr('checked','checked');
		}
		
		$(".itemType").each(function() {
			if ($(this).attr("checked") != "checked") {
				// 变成没选中时，数量、分值清空，文本框不可用
				$(this).siblings().val("");
				$(this).siblings().each(function() {
					$(this).attr("disabled", true);
				});
			} 
		});
	}
	
// 试题类型，复选框选中与取消事件
	$(".itemType").click(function() {
		if ($(this).attr("checked") != "checked") {
			// 变成没选中时，数量、分值清空，文本框不可用
			$(this).siblings().val("");
			$(this).siblings().each(function() {
				$(this).attr("disabled", "disabled");
			});
		} else {
			// 变成选中时，文本框解除不可用
			$(this).siblings().each(function() {
				$(this).attr("disabled", false);
			});
		}
	});
	
// 计算试题总数与总分数（调用方法）
	function countItem(){
		allNum = 0;
		allScore = 0;
		$(".itemNum").each(
				function() {
					if ($(this).val().match(/^\d+$/)
							&& $(this).val() != "") {
						allNum = allNum + parseInt($(this).val());
						if ($(this).next().val().match(/^\d+$/)
								&& $(this).next().val() != "") {
							allScore = allScore
									+ parseInt($(this).val())
									* parseInt($(this).next().val());

						}
						$("#allScore").val(allScore);
						$("#allNum").val(allNum);
					}
				});
	}

// 确定试题分数和数量为数字,计算试题总数与总分数
	$(".itemNum").add(".itemScore").blur(
			function() {
				
				// 确定试题分数和数量为数字且不为0
				if (!($(this).val().match(/^\d+$/)) && $(this).val() != "") {
					alert("试题数量或试题分数只能为数字！");
					$(this).val("");
					return;
				} else {
					if (parseInt($(this).val()) == 0) {
						alert("试题数量或试题分数不能为0！");
						$(this).val("");
						return;
					}
				}
				countItem();
				
			});

//点击试题类型复选框，重新计算试题总数与总分数
	$(".itemType").click(function(){
		countItem();
	});

//返回按钮
	$("#backButton").click(function(){
		window.location.href="listPaper.action?gradeLevel="+$("#gradeLevel").val();
	});
	
// 表单提交校验
	$("#addPaperForm").submit(
			function() {
				if ($("#gradeInfoList").val() == "") {
					alert("请选择所属年级！");
					$("#gradeInfoList").focus();
					return false;
				}
				if ($("#courseMap").val() == "--请选择--") {
					alert("请选择所属科目！");
					$("#courseMap").focus();
					return false;
				}
				if ($("#title").val() == "") {
					alert("请填写试卷题目！");
					$("#title").focus();
					return false;
				}
				if ($("#single").attr("checked")) {
					if ($("#itemNumSingle").val() == ""
							|| $("#itemScoreSingle").val() == "") {
						alert("请填写单选题数量和分值！");
						return false;
					}

				}
				if ($("#double").attr("checked")) {
					if ($("#itemNumDouble").val() == ""
							|| $("#itemScoreDouble").val() == "") {
						alert("请填写多选题数量和分值！");
						return false;
					}

				}
				if ($("#subjective").attr("checked")) {
					if ($("#itemNumSubjective").val() == ""
							|| $("#itemScoreSubjective").val() == "") {
						alert("请填写简答题数量和分值！");
						return false;
					}
				}
			});

});

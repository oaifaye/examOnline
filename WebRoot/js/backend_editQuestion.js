//非dwr部分
$(function(){

//修改时，初始化页面
	if(""!=$("#id").val()){
		
		//试题材料添加方式
		$("input[name=questionMaterialType]").each(function(){
			if($(this).val()==$("#questionMaterialType").val()){
				$(this).attr('checked','checked');
			}
		});
		//如果有材料
		if("1"==$("input[name=questionMaterialType]:checked").val()){
			$("#questionMaterialContent").add(("#questionMaterialList")).show();
		}
		//试题类型选中
		$("input[name=questionType]").each(function(){
			if($(this).val()==$("#questionType").val()){
				$(this).attr('checked','checked');
			}
		});
		//试题类型显示
		if("0"==$("input[name=questionType]:checked").val()||"1"==$("input[name=questionType]:checked").val()){
			objectiveItemTab();
			//客观题答案
			var answers = new Array();
			answers=$("#objectiveAnswer").val().split(",");
			
			for(var i = 0 ; i<answers.length;i++){
				
				$("input[name=objectiveAnswer]").each(function(){
					if($(this).val()==answers[i]){
						$(this).attr('checked','checked');
					}
				});
			}
		}else if("2"==$("input[name=questionType]:checked").val()){
			subjectiveItemTab();
			//客观题答案不显示但默认4个
			$("#optionNum").val(4);
		}
		
	}else{
//添加时，初始化页面
		//客观题答案默认4个
		$("#optionNum").val(4);
	}
	
	
//试题材料添加方式切换
	$("input[name=questionMaterialType]").click(function(){
		$("#questionMaterialList").add("#questionMaterialContent").hide();
		if($(this).val()!="0"){
			//$(this).attr('checked','checked');
			$("#questionMaterialContent").show();
			if($(this).val()=="1"){
				$("#questionMaterialList").show();
				//下方文本框带出材料内容
				$("#questionMaterialContent").attr("value",$("#questionMaterialList option:selected").text());
			}
			if($(this).val()=="2"){
				//点击添加材料，清空选择材料中的内容
				$("#questionMaterialContent").attr("value","");
			}
		}
	});
	
//选择材料后，下方文本框带出材料内容
	$("#questionMaterialList").change(function(){
		if($("#questionMaterialList option:selected").text()!="--请选择--"){
			$("#questionMaterialContent").attr("value",$("#questionMaterialList option:selected").text());
		}else{
			$("#questionMaterialContent").attr("value","");
		}
	});
	
//主客观题切换，客观题（被调用方法）
	function objectiveItemTab(){
		$(".objectiveItem").show();
		$(".subjectiveItem").hide();
		//点击主观题radio，默认选项个数(只需修改页面select中的value)
		for(var i = 0;i<parseInt($("#optionNum").val());i++){
			//选项显示
			$("#questionTable tr").eq(i+8).show();
			//答案显示
			$("#answerTd label").eq(i).show();
			
		}
	}
	
//主客观题切换，主观题（被调用方法）
	function subjectiveItemTab(){
		$(".subjectiveItem").show();
		$(".objectiveItem").hide();
		for(var i=0;i<8;i++){
			$("#questionTable tr:td").eq(i+8).hide();
			$("#answerTd label").eq(i).hide();
		}
	}
	
//主客观题切换
	$("#single").add("#double").click(function(){
		objectiveItemTab();
		//清空所有选中答案
		$("input[name='objectiveAnswer']").removeAttr('checked');
	});
	$("#subjective").click(function(){
		subjectiveItemTab();
	});
	
//选项个数select事件
	$("#optionNum").change(function(){
		//隐藏所有选项
		for(var i=0;i<8;i++){
			$("#questionTable tr:td").eq(i+8).hide();
			$("#answerTd label").eq(i).hide();
		}
		//打开需要的选项
		for(var i = 0;i<parseInt($("#optionNum").val());i++){
			$("#questionTable tr:td").eq(i+8).show();
			$("#answerTd label").eq(i).show();
		}
	});
	
//根据单选或是多选，控制答案个数
	$("input[name='objectiveAnswer']").each(function(){
		$(this).click(function(){
			if($('input[name="questionType"]:checked').val()=='0'){
				if($(this).attr('checked')){
					$("input[name='objectiveAnswer']").removeAttr('checked');
					$(this).attr('checked','checked');
				}
			}
		});
	});
	
//返回按钮
	$("#backButton").click(function(){
		window.location.href="listQuestion.action?gradeLevel="+$("#gradeLevel").val();
	});
	
//表单提交校验
	$("#questuionForm").submit(function(){
		//清空不需要的选项与答案
		if(parseInt($("#optionNum").val())!=8){
			for(var i=parseInt($("#optionNum").val());i<8;i++){
				$("#questionTable tr:td").eq(i+8).find("textarea").val("");
				$("input[name='objectiveAnswer']").eq(i).attr("checked","");
			}
		}
		//校验数据
		if(""==$("#gradeInfoList").val()){
			alert("请选择试题所属年级！！！");
			$("#gradeInfoList").focus();
			return false;
		}
		if("--请选择--"==$("#courseMap").val()){
			alert("请选择试题科目！！！");
			$("#courseMap").focus();
			return false;
		}
		if(""==$("#questionTitle").val().replace(/[ ]/g,"")){
			alert("请输入试题标题！！！");
			$("#questionTitle").focus();
			return false;
		}
		
		if("0"!=$("input[name=questionMaterialType]:checked").val()){
			//选择材料
			if("1"==$("input[name=questionMaterialType]:checked").val()){
				if(""==$("#questionMaterialList").val()){
					alert("请选择试题材料！！！");
					$("#questionMaterialList").focus();
					return false;
				}
			}
			//新增或修改材料
			if(""==$("#questionMaterialContent").val().replace(/[ ]/g,"")){
				
					alert("请选择填写试题材料！！！");
					$("#questionMaterialContent").focus();
					return false;
			}
			
		}
		
		if(""==$("#questionContent").val().replace(/[ ]/g,"")
				||$("#questionContent").val().replace(/[ ]/g,"").length>2000){
			alert("试题题干不能为空且字数不超过2000！！！");
			$("#questionContent").focus();
			return false;
		}
		if("undefined"==typeof($('input[name="questionType"]:checked').val())){
			alert("请选择试题类型！！！");
			return false;
		
		}
		//客观题数据校验
		if("2"!=$("input[name=questionType]:checked").val()){
			for(var i = 0;i<parseInt($("#optionNum").val());i++){
				//判断特定数量的每个选项是否填写
				if(""==$("#questionTable tr:td").eq(i+8).find("textarea").val().replace(/[ ]/g,"")
						||$("#questionTable tr:td").eq(i+8).find("textarea").val().replace(/[ ]/g,"").length>2000){
					alert("请保证前"+$("#optionNum").val()+"个选项不为空且不超过2000！！！");
					$("#questionTable tr:td").eq(i+8).find("textarea").focus();
					return false;
				}
			}
			//判断答案个数是否为0
			var answerArray =[];
			$("input[name='objectiveAnswer']:checked").each(function(){    
				answerArray.push($(this).val());    
			});    
			if(answerArray.length==0){
				alert("答案个数不能为0");
				return false;
			}
			//去掉主观题内容
			$(".subjectiveItem").html("");
		}
		if("2"==$("input[name=questionType]:checked").val()){
			if(""==$("#subjectiveAnswer").val().replace(/[ ]/g,"")){
				alert("请输入试题答案");
				$("#subjectiveAnswer").focus();
				return false;
			}
			//去掉客观题内容
			$(".objectiveItem").add(".objective").html("");
		}
	});
	
});
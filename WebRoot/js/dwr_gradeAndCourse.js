		$(function(){			
			//定义DWRUtil，否则报错
			if (typeof window["DWRUtil"] == "undefined") {  
        		window.DWRUtil = dwr.util;  
    		}  
    		
			//修改时，初始化科目下拉框
			if($("#gradeInfoList").val()!=""){
				var array=new Array();
				array[0]=$("#gradeInfoList").val();
				array[1]="1";
				baseInfoService.findCourseInfoByGradeID(array,
					function(data){
						DWRUtil.removeAllOptions("courseMap"); 
						DWRUtil.addOptions("courseMap", ["--请选择--"]); 
 						DWRUtil.addOptions("courseMap", data); 
 						
 						//确定辅导年级选中项
 	     				if($("#gradeName").val()!=""){
 							DWRUtil.setValue("courseMap",$("#courseName").val());
 						}
					}
				);
			}
			
			//根据辅导年级id初始化科目下拉框
			$("#gradeInfoList").change(function(){
				if($(this).val()!=""){
					var array=new Array();
					array[0]=$("#gradeInfoList").val();
					array[1]="1";
					baseInfoService.findCourseInfoByGradeID(array,
						function(data){
							DWRUtil.removeAllOptions("courseMap"); 
							DWRUtil.addOptions("courseMap", ["--请选择--"]); 
     						DWRUtil.addOptions("courseMap", data); 
						}
					);
				}
			});
		});
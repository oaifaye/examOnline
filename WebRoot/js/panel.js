$(function(){
			
			$(".tabTop li").click(function(){
				if (!$(this).hasClass('current')){ 
					var winHeight = $(window).height();
					$("#greybackground").css({"height":winHeight});
					$("#tabmenu1 li").removeClass(); 
					$(this).addClass("current");    
					$(".tabFoot").slideUp("fast");
					$(".tabTop").find("span").addClass("imgClass");
					$(this).find("span").removeClass();
					$(".tabFoot:eq("+$("#tabmenu1 > li").index(this)+")").slideDown("fast");
				}else{
					$("#greybackground").css({"height":"0px"});
					$(".tabFoot").slideUp("fast");
					$(".tabTop").find("span").removeClass();
					$("#tabmenu1 li").removeClass("current");
				}
			});
			
			//黑色div层点击后，div层高设为0px、所有打开的列表收缩、图像恢复明亮、top背景去掉
			$("#greybackground").click(function(){
				
				$("#greybackground").css({"height":"0px"});
				$(".tabFoot").slideUp("fast");
				$("span").removeClass();
				$("#tabmenu1 li").removeClass("current");
			});
		});
			
			
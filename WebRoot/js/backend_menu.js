		$(function(){
			//图标相对窗口静止不动
			$(window).scroll(function(){
				//$(document).scrollTop() : 设置 <div> 元素中滚动条的垂直偏移：
				//parseInt : 解析一个字符串，并返回一个整数
				nowtop = parseInt($(document).scrollTop());	
				$('#cleft_box').css('top', nowtop + 100 + 'px');
			});
			
			//菜单按钮动画
			$(".cleft_top").hover(
				function(){
					
					$(".cleft_top").css({"background":"url(images/button9.png) left top no-repeat"});
					$(".cleft_foot ul li").css({"display":"block"});
				},
				//.cleft_top 移出
				function(){
					
					$(".cleft_foot li").hover(
						//从.cleft_foot li移入
						function() {
							
							$(this).find("ul.childnav").css({"display":"inline"});
							$(this).hover(
								function(){}, 
								function(){
									$(this).parent().find("ul.childnav").css({"display":"none"});
								}
							);
						},
						//从.cleft_foot li移出
						function(){
							$(".cleft_foot > ul").hover(
								function() {},
								function(){
									$(".cleft_top").css({"background":"url(images/button7.png) left top no-repeat"});
									$(".cleft_foot ul li").css({"display":"none"});
								}
							);
						}
					);
				}
			);
			
			$(".cleft_top").click(function(){
				window.location.href="backend/index.jsp";
			});
		});
	
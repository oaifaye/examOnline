$(function(){
	//设置div greybackground的高与body一样
	var winHeight = $(document).height();
	$(".greybackground").css({"height":winHeight});
	//背景相对窗口静止不动
	$(window).scroll(function(){
		var nowtop = parseInt($(document).scrollTop());
		$(".greybackground").css('top', nowtop  + 'px');
	});
});
//메인 헤더 스크롤
var lastScrollTop = 0,
delta = 15;
$(window).scroll(function(event) {
	var st = $(this).scrollTop();
	if (Math.abs(lastScrollTop - st) <= delta) return;
	if ((st > lastScrollTop) && (lastScrollTop > 0)) {
		$("#main_header").addClass("on");
		$("#main_header .logo #main_bg").attr("src", "/nnd/resources/images/logo.png");
	} else {
		$("#main_header").removeClass("on");
		$("#main_header .logo #main_bg").attr("src", "/nnd/resources/images/logo2.png")
	}
	lastScrollTop = st;
});

//$(document).ready(function(){
//	//탑메뉴
//	$( '.top span' ).click( function() {
//		$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
//	    return false;
//	);
//});


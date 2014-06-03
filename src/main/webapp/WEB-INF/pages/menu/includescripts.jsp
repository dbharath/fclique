<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='<c:url value="/static/css/style.css"/>' rel='stylesheet' type='text/css' />
<link href='<c:url value="/static/css/todaydeals.css"/>' rel='stylesheet' type='text/css' />
<link href='<c:url value="/static/css/megamenu.css"/>' rel="stylesheet" type="text/css" media="all" />
<link href='<c:url value="/static/css/slippry.css"/>' rel="stylesheet" >
<link href='<c:url value="/static/css/coupons.css"/>' rel="stylesheet" >

<script type="text/javascript" src='<c:url value="/static/js/jquery.1.9.1.min.js"/>'></script>		
<script type="text/javascript" src='<c:url value="/static/js/jquery.easy-ticker.js"/>'></script>		
<script type="text/javascript" src='<c:url value="/static/js/megamenu.js"/>'></script>		
<script type="text/javascript" src='<c:url value="/static/js/move-top.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/easing.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/menu_jquery.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/jquery-ui.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/scripts-f0e4e0c2.js"/>'></script>
<script type="text/javascript">
	jQuery(document).ready(function($) { 
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
		});
		$('#demo').hide();
		$('.vticker').easyTicker();
		$(".megamenu").megamenu();
		jQuery('#jquery-demo').slippry({
			  // general elements & wrapper
			  slippryWrapper: '<div class="sy-box jquery-demo" />', // wrapper to wrap everything, including pager
			  // options
			  adaptiveHeight: false, // height of the sliders adapts to current slide
			  useCSS: false, // true, false -> fallback to js if no browser support
			  autoHover: false,
			  transition: 'fade'
		});

	});
	
</script>		
<script type="application/x-javascript"> 
	addEventListener("load", function() { 
		setTimeout(hideURLbar, 0); 
	}, false); 
	function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<style>
body {
font: 100% Arial, Helvetica, sans-serif;
color: #333333;
margin: 0;
padding: 0;
line-height: 18px;
font-weight: normal;
background: #f5f5f5;
}

</style>
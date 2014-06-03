<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="header layoutSmall">
  <!-- 
	<div class="top-header">
		<div class="wrap">
			<div class="top-header-left">
				<ul>
					
					<script type="text/javascript">
						$(function(){
						    var $cart = $('#cart');
						        $('#clickme').click(function(e) {
						         e.stopPropagation();
						       if ($cart.is(":hidden")) {
						           $cart.slideDown("slow");
						       } else {
						           $cart.slideUp("slow");
						       }
						    });
						    $(document.body).click(function () {
						       if ($cart.not(":hidden")) {
						           $cart.slideUp("slow");
						       } 
						    });
						    });
					</script>
					
					<li><a class="cart" href="#"><span id="clickme"> </span></a></li>
					
					<div id="cart">Your Cart is Empty <span>(0)</span></div>
					
					<li><a class="info" href="#"><span> </span></a></li>
				</ul>
			</div>
			<div class="top-header-center">
				<div class="top-header-center-alert-left">
					<h3>FREE DELIVERY</h3>
				</div>
				<div class="top-header-center-alert-right">
					<div class="vticker">
						<ul>
							<li>Applies to orders of $50 or more. <label>Returns are always free.</label></li>
						</ul>
					</div>
				</div>
				<div class="clear"> </div>
			</div>
			<div class="top-header-right">
				<ul>
					<li><a href="login.html">Login</a><span> </span></li>
					<li><a href="register.html">Join</a></li>
				</ul>
			</div>
			<div class="clear"> </div>
		</div>
	</div>
	 -->
	<div class="header-bottom">
		<div class="wrap">
			<ul class="megamenu skyblue">
				<li class="grid">
					<a class="color2" href='<c:url value="/index"/>'>Home</a>								
				</li>
	  			<li class="grid"><a class="color4" href="#">Merchant Offer</a>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Flipkart</h4>
									<ul>
										<li><a href='<c:url value="/products"/>'>offer products</a></li>
										<li><a href='<c:url value="/products"/>'>Discounts</a></li>
										<li><a href='<c:url value="/products"/>'>Coupons</a></li>
										<li><a href='<c:url value="/products"/>'>Cashback</a></li>										
									</ul>	
								</div>	
								<div class="h_nav">
									<h4>help</h4>
									<ul>
										<li><a href="products.html">trends</a></li>
										<li><a href="products.html">sale</a></li>
										<li><a href="products.html">style videos</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>my company</h4>
									<ul>
										<li><a href="products.html">trends</a></li>
										<li><a href="products.html">sale</a></li>
										<li><a href="products.html">style videos</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>												
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>account</h4>
									<ul>
										<li><a href="products.html">login</a></li>
										<li><a href="products.html">create an account</a></li>
										<li><a href="products.html">create wishlist</a></li>
										<li><a href="products.html">my shopping bag</a></li>
										<li><a href="products.html">brands</a></li>
										<li><a href="products.html">create wishlist</a></li>
									</ul>	
								</div>						
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>my company</h4>
									<ul>
										<li><a href="products.html">trends</a></li>
										<li><a href="products.html">sale</a></li>
										<li><a href="products.html">style videos</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>popular</h4>
									<ul>
										<li><a href="products.html">new arrivals</a></li>
										<li><a href="products.html">men</a></li>
										<li><a href="products.html">women</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>
							</div>
							<div class="col1 women">
								<div class="women-pic">
									<img src="images/women.png" title="" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    				</div>
				</li>				
				<li class="grid"><a class="color4" href='<c:url value="/coupons/index"/>'>Coupons</a>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Men</h4>
									<ul>
										<li><a href='<c:url value="/products"/>'>offer products</a></li>
										<li><a href='<c:url value="/products"/>'>Discounts</a></li>
										<li><a href='<c:url value="/products"/>'>Coupons</a></li>
										<li><a href='<c:url value="/products"/>'>Cashback</a></li>										
									</ul>	
								</div>				
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Women</h4>
									<ul>
										<li><a href="products.html">trends</a></li>
										<li><a href="products.html">sale</a></li>
										<li><a href="products.html">style videos</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>												
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Kids</h4>
									<ul>
										<li><a href="products.html">login</a></li>
										<li><a href="products.html">create an account</a></li>
										<li><a href="products.html">create wishlist</a></li>
										<li><a href="products.html">my shopping bag</a></li>
										<li><a href="products.html">brands</a></li>
										<li><a href="products.html">create wishlist</a></li>
									</ul>	
								</div>						
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Entertainment</h4>
									<ul>
										<li><a href="products.html">trends</a></li>
										<li><a href="products.html">sale</a></li>
										<li><a href="products.html">style videos</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Food</h4>
									<ul>
										<li><a href="products.html">new arrivals</a></li>
										<li><a href="products.html">men</a></li>
										<li><a href="products.html">women</a></li>
										<li><a href="products.html">accessories</a></li>
										<li><a href="products.html">kids</a></li>
										<li><a href="products.html">style videos</a></li>
									</ul>	
								</div>
							</div>							
						</div>						
	    			</div>
					
				</li>
				<!-- <li class="inline-search-box">
					<div class="mid-grid-input pull-right">
						<form action="/product/search" method="post" id="search_form"> 
							<input type="text" name="q" id="search_box" placeholder="What Are You Looking for?" />
						</form>
					</div>	
				</li>
				 -->
			</ul>		
		</div>
	</div>
</div>	


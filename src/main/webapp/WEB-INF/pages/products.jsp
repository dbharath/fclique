<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="menubar.jsp" flush="true"/>
<div class="content product-box-main">
	<div class="wrap">		
		<div class="content-right product-box">
			<div class="product-box-head">
				<div class="product-box-head-left">
					<h3>Products <span>(500)</span></h3>
				</div>
				<div class="product-box-head-right">
					<ul>
						<li><span>Sort ::</span><a href="#"> </a></li>
						<li><label> </label> <a href="#"> Popular</a></li>
						<li><label> </label> <a href="#"> New</a></li>
						<li><label> </label> <a href="#"> Discount</a></li>
						<li><span>Price ::</span><a href="#">Low High</a></li>
					</ul>
				</div>
				<div class="clear"> </div>
			</div>
			<div class="product-grids">	
				<c:if test="${fn:length(siteResponses) > 0}">
				 <c:set var="count" scope="page" value="0"/>	
			      <c:forEach items="${siteResponses}" var="siteResponse"> 			      
			      	<c:if test="${siteResponse.products != null && fn:length(siteResponse.products) > 0}">
			      		<c:forEach items="${siteResponse.products}" var="productDetail">
			      			<c:set var="count" scope="page" value="${count + 1}"/>
			      			<div class="product-grid fade <c:if test="${count%3 == 0}" >last-grid</c:if>" onclick="location.href='details.html';">
			      				<div class="product-grid-head">									
									<div class="block">
										<div class="starbox small ghosting"> </div> <span> (46)</span>
									</div>
								</div>
								<div class="product-pic">
									<a href="#"><img src="${productDetail.imageUrl}" title="product-name" /></a>								
								</div>
								<div class="product-detail">
									<p>
										<a href="#">${productDetail.name}</a>
									</p>
								</div>
								<div class="product-info">
									<div class="product-info-cust">
										<a href="${productDetail.productLink}" target="_blank">Details</a>
									</div>
									<div class="product-info-price">
										<a href="details.html">Rs. ${product.price}</a>
									</div>
									<div class="clear"> </div>
								</div>
			      			</div>	
			      		</c:forEach>			      				        
			      	</c:if>
			      </c:forEach>
	            </c:if>
			</div>				
		</div>
		<div class="clear"> </div>
	</div>
</div>	
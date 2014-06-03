<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<style>
  .pageDeals .deal3 .dealBox .discoutBshop{ width:40px; height:26px; background:#ff6000; color:#fff; position:absolute; text-align:center; line-height:10px; padding-top:5px; top:0px; left:0px; z-index:999;}
                    .pageDeals .deal3 .dealBox .discoutBshop .num{ font:bold 15px/10px Arial, Helvetica, sans-serif;}
                    .pageDeals .deal3 .dealBox .discoutBshop .per{font:bold 12px/10px Arial, Helvetica, sans-serif; padding-left:0px;}
                    .pageDeals .deal3 .dealBox .discoutBshop .off{ display:block;font:10px Arial, Helvetica, sans-serif; margin-top:-2px;}
</style>
<jsp:include page="../menu/includescripts.jsp" />
<jsp:include page="../menu/menu.jsp" />

<div class="content product-box-main">
	<div class="wrap">		
		<div class="content-container">
			<div class="pageDeals" style="padding-bottom: 0px;">
				<ul class="multiDeal deal4 deal3 show4"	style="width: auto; margin-left: 24px;">					
					<c:if test="${products.content != null && fn:length(products.content) > 0}">
						<c:set var="cont" scope="page" value="${products.content }"/>
			      		<c:forEach items="${cont}" var="productDetail">
			      			<li class="dealBox prod" style="border: 0px solid red;">
								<figure>
									<a href="${productDetail.productUrl}"	target="_blank" class="prodImg" style="background: #fff;"> 
										<img src="${productDetail.image4}"	alt="${productDetail.productName}" title="${productDetail.productName}" class="img-border" />
									</a>
									<span class="discoutBshop"><span class="num">${productDetail.discount}</span><span
										class="per">%</span><span class="off">OFF</span></span>
									
									<figcaption>
										<a href="${productDetail.productUrl}" title="${productDetail.productName}" target='_blank'>
											${productDetail.productName}
										</a>
									</figcaption>					
									<ul class="buybox" style="margin-top: 18px !important;">
										<li class="disprice ">
											<a target="_blank" href="${productDetail.productUrl}">${productDetail.mrp}</a>
										</li>
										<li class="price  ">
											<a target="_blank" href="${productDetail.productUrl}">${productDetail.price}</a>
										</li>
										<!--a  class="btnBuy2" onclick="window.location='http://shopping.indiatimes.com/Fashion/Phoenix-Black-Men-Stretchable-Chinos---cn/43966/p_B2546483?utm_source=network&utm_medium=timesdeal&utm_campaign=productsection9'" href="javascript:void(0)">Buy Now</a>-->
										<a class="btnBuy2" href="${productDetail.productUrl}"	target="_blank">Buy Now</a>
									</ul>
						
								</figure> 
							</li>
			      		</c:forEach>
			      	</c:if>					
				</ul>	
			</div>
		</div>
	</div>
</div>
</html>
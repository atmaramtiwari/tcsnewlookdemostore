<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="varstatus" required="false" type="java.lang.Integer" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>



 
<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>
<c:set value="${not empty product.potentialPromotions}" var="hasPromotion"/>







<li class="product-item" id="trigger${varstatus}">
	<ycommerce:testId code="product_wholeProduct">
		<a class="thumb" href="${productUrl}" title="${product.name}">
			<product:productPrimaryImage product="${product}" format="product"/>
		</a>
		<div class="details">

			<ycommerce:testId code="product_productName"><a class="name" href="${productUrl}">${product.name}</a></ycommerce:testId>
		

			<c:if test="${not empty product.potentialPromotions}">
				<div class="promo">
					<c:forEach items="${product.potentialPromotions}" var="promotion">
						${promotion.description}
					</c:forEach>
				</div>
			</c:if>

			<ycommerce:testId code="product_productPrice">
				<div class="price"><format:price priceData="${product.price}"/></div>
			</ycommerce:testId>

		</div>


		<c:set var="product" value="${product}" scope="request"/>
		<c:set var="addToCartText" value="${addToCartText}" scope="request"/>
		<c:set var="addToCartUrl" value="${addToCartUrl}" scope="request"/>
		<c:set var="isGrid" value="true" scope="request"/>
		<div class="addtocart">
			<div class="actions-container-for-${component.uid}">
				<action:actions element="div" parentComponent="${component}"/>
				<div class="compare_chk">
					<input id="checkbox1" type="checkbox"
						data-unique-itemid="trigger${varstatus}" value="value1"
						data-product-id="${product.code}" /> Add to Compare
				</div>

			</div>
		</div>
	</ycommerce:testId>
</li>
<%-- <div id="light" class="white_content">This is the lightbox content. <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">Close</a></div> --%>
<<!-- div id="light" class="white_content">This is the lightbox content. <a href = "/newlookstorefront/productcompare">Close</a></div>
		<div id="fade" class="black_overlay"></div> -->

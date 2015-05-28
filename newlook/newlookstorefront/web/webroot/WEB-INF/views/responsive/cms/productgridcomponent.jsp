<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/productcompare" var="productCompareUrl"/>
<div class="col-md-9 col-lg-10">
	<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}"/>
	 <%-- Compare section Starts --%>

	<div class="compare_cart_wrapper" id="container" style="display:none"> 
		<div class="compare_controls">
			<%-- <a href="#compare_popup"
				onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"
				class="btn_blue">Compare</a> --%>
				<a href="javascript:void(0);" class="btn_blue compare_btn">Compare</a>
		</div>
	</div>
	<!-- Compare section ends -->
	<ul class="product-listing product-grid">
		<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
			
			<product:productListerGridItem product="${product}" varstatus="${status.index}" />
		</c:forEach>
	</ul>
 
	<div id="addToCartTitle" style="display:none">
		<div class="add-to-cart-header">
			<div class="headline">
				<span class="headline-text"><spring:theme code="basket.added.to.basket"/></span>
			</div>
		</div>
	</div>

	<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
</div>


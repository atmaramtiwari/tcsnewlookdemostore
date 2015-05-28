<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-62588293-1', 'auto');
  ga('send', 'pageview');

</script>

<c:if test="${not empty googleAnalyticsTrackingId}">
<script type="text/javascript">
/* Google Analytics */

var googleAnalyticsTrackingId = 'UA-62588293-1';



var _gaq = _gaq || [];
_gaq.push(['_setAccount', googleAnalyticsTrackingId]);

<c:choose>
	<c:when test="${pageType == 'PRODUCT'}">
		<c:set var="categories" value="" />
		<c:forEach items="${product.categories}" var="category">
			<c:set var="categories">${categories},${category.name}</c:set>
		</c:forEach>
		_gaq.push(['_setCustomVar', 2, 'CategoryOfProduct', '${fn:substringAfter(categories, ',')}', 3]);
	</c:when>

	<c:when test="${pageType == 'CATEGORY'}">
		<c:choose>
			<c:when test="${searchPageData.pagination.totalNumberOfResults > 0}">
				<c:if test="${not empty searchPageData.breadcrumbs}">
					<c:forEach items="${searchPageData.breadcrumbs}" var="breadcrumb">
						_gaq.push(['_trackEvent', 'category', '${breadcrumb.facetName}', '${breadcrumb.facetValueName}']);
					</c:forEach>
				</c:if>
			</c:when>
			
			<c:otherwise>
				_gaq.push(['_setCustomVar', 1, 'ZeroResults', '${searchPageData.freeTextSearch}', 3]);
			</c:otherwise>
		</c:choose>
		
		_gaq.push(['_trackPageview']);
	</c:when>
	
	<c:when test="${pageType == 'PRODUCTSEARCH'}">
		<c:choose>
			<c:when test="${searchPageData.pagination.totalNumberOfResults > 0}">
				<c:if test="${not empty searchPageData.breadcrumbs}">
					<c:forEach items="${searchPageData.breadcrumbs}" var="breadcrumb">
						_gaq.push(['_trackEvent', 'facet', '${breadcrumb.facetName}', '${breadcrumb.facetValueName}']);
					</c:forEach>
				</c:if>
			</c:when>
			
			<c:otherwise>
				_gaq.push(['_setCustomVar', 1, 'ZeroResults', '${searchPageData.freeTextSearch}', 3]);
			</c:otherwise>
		</c:choose>
		
		_gaq.push(['_trackPageview']);
	</c:when>
	<c:when test="${pageType == 'ORDERCONFIRMATION'}">
		_gaq.push([
	 		 '_addTrans',
	 		 '${orderData.code}',
	 		 '${siteName}',
	 		 '${orderData.totalPrice.value}',
	 		 '${orderData.totalTax.value}',
	 		 '${orderData.deliveryCost.value}',
	 		 '${orderData.deliveryAddress.town}',
	 		 '${orderData.deliveryAddress.postalCode}',
	 		 '${orderData.deliveryAddress.country.name}'
	 	]);
	 	<c:forEach items="${orderData.entries}" var="entry">
	 		_gaq.push([
	 		    '_addItem',
	 			'${orderData.code}',
	 			'${entry.product.code}',
	 			'${entry.product.name}',
	 			<c:choose>
		 			<c:when test="${not empty entry.product.categories}">
		 				'${entry.product.categories[fn:length(entry.product.categories) - 1].name}',
		 			</c:when>
		 			<c:otherwise>
		 				'',
		 			</c:otherwise>
	 			</c:choose>
	 			'${entry.product.price.value}',
	 			'${entry.quantity}'
	 		]);
	 	</c:forEach>
	 	_gaq.push(['_trackTrans']);
	</c:when>
	<c:otherwise>
		_gaq.push(['_trackPageview']);
	</c:otherwise>
</c:choose>

(function() {
	var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();


function trackAddToCart_google(productCode, quantityAdded) {
	_gaq.push(['_trackEvent', 'Cart', 'AddToCart', productCode, quantityAdded]);
}

function trackUpdateCart(productCode, initialQuantity, newQuantity) {
	if (initialQuantity != newQuantity) {
		if (initialQuantity > newQuantity) {
			_gaq.push(['_trackEvent', 'Cart', 'RemoveFromCart', productCode, initialQuantity - newQuantity]);
		} else {
			_gaq.push(['_trackEvent', 'Cart', 'AddToCart', productCode, newQuantity - initialQuantity]);
		}
	}
}

function trackRemoveFromCart(productCode, initialQuantity) {
	_gaq.push(['_trackEvent', 'Cart', 'RemoveFromCart', productCode, initialQuantity]);
}

window.mediator.subscribe('trackAddToCart', function(data) {
	if (data.productCode && data.quantity)
	{
		trackAddToCart_google(data.productCode, data.quantity);
	}
});

window.mediator.subscribe('trackUpdateCart', function(data) {
	if (data.productCode && data.initialCartQuantity && data.newCartQuantity)
	{
		trackUpdateCart(data.productCode, data.initialCartQuantity, data.newCartQuantity);
	}
});

window.mediator.subscribe('trackRemoveFromCart', function(data) {
	if (data.productCode && data.initialCartQuantity)
	{
		trackRemoveFromCart(data.productCode, data.initialCartQuantity);
	}
});
</script>
</c:if> 
<link href="productCompare.css" type="text/css" rel="stylesheet"><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<%-- <c:forEach items="${fields}" var="field" varStatus="count">

	<tr>
		<c:set var="fieldName" value="${field.name}"></c:set>
		<c:set var="fieldValue" value="${productCompareDTO[0]}.${fieldName}"/>
		<td>${fieldName }</td>

		<td>${productCompareDTO[0][field.name]}</td>
	</tr>

</c:forEach> --%>





<div id="light" class="white_content">
	<h1>
		Compare <span>${productCompareDTO[0].name}</span>
	</h1>

	<p>You can add upto 4 Product to compare</p>
	<div class="compare_product">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td width="10%">&nbsp;Image</td>
					<td width="25%"><label>${productCompareDTO[0].name} </label>
						<div class="prdct_comp">
							<img src="${productCompareDTO[0].media.URL}" />
						</div></td>
					<c:if test="${not empty productCompareDTO[1]}">
						<td width="25%"><label>${productCompareDTO[1].name} </label>
							<div class="prdct_comp">
								<img src="${productCompareDTO[1].media.URL}">
							</div></td>
					</c:if>
					<c:if test="${not empty productCompareDTO[2] }">
						<td width="25%"><label>${productCompareDTO[2].name} </label>

							<div class="prdct_comp">
								<img src="${productCompareDTO[2].media.URL}" />
							</div></td>

					</c:if>
					<c:if test="${not empty productCompareDTO[3]}">
						<td width="25%"><label>${productCompareDTO[3].name} </label>

							<div class="prdct_comp">
								<img src="${productCompareDTO[3].media.URL}" />
							</div></td>
					</c:if>
				</tr>
				<c:forEach items="${fields}" var="field" varStatus="count">
					<c:if
						test="${field.name ne stockStatus || field.name ne quantity || field.name ne price || field.name ne name}">
						<tr>
							<td>${field.name}</td>
							<td><span class="${field.name}">${productCompareDTO[0][field.name]}</span></td>
							<c:if test="${not empty productCompareDTO[1]}">
								<td><span class="price">
										${productCompareDTO[1][field.name]}</span></td>
							</c:if>
							<c:if test="${not empty productCompareDTO[2]}">
								<td><span class="price">
										${productCompareDTO[2][field.name]}</span></td>
							</c:if>
							<c:if test="${not empty productCompareDTO[3]}">
								<td><span class="price">${productCompareDTO[3][field.name]}</span></td>
							</c:if>
							<td>&nbsp;</td>
						</tr>
					</c:if>
				</c:forEach>

				<tr>
					<td>Availability</td>
					<c:choose>
						<c:when test="${productCompareDTO[0].quantity eq 0}">
							<td><label title="${productCompareDTO[0].stockStatus}"
								style="color: green"></label></td>
						</c:when>
						<c:otherwise>
							<td><label title="${productCompareDTO[0].stockStatus}"></label>
								</br> <input type="submit" class="orng_btn" value="ADD TO CART" /> <a
								href="" class="orng_btn" title="ADD TO CART">ADD TO CART</a></td>
						</c:otherwise>
					</c:choose>
					<c:if test="${not empty productCompareDTO[1]}">
						<c:choose>
							<c:when test="${productCompareDTO[1].quantity eq 0}">
								<td><label title="${productCompareDTO[1].stockStatus}"
									style="color: green"></label></td>
							</c:when>
							<c:otherwise>
								<td><label title="${productCompareDTO[1].stockStatus}"></label>
									</br> <input type="submit" class="orng_btn" value="ADD TO CART" /></td>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${not empty productCompareDTO[2]}">
						<c:choose>
							<c:when test="${productCompareDTO[2].quantity eq 0}">
								<td><label title="${productCompareDTO[2].stockStatus}"
									style=""></label></td>
							</c:when>
							<c:otherwise>
								<td><label title="${productCompareDTO[2].stockStatus}"
									style=""></label> </br> <input type="submit" class="orng_btn"
									value="ADD TO CART" /></td>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${not empty productCompareDTO[3]}">
						<c:choose>
							<c:when test="${productCompareDTO[3].quantity eq 0}">
								<td><label title="${productCompareDTO[3].stockStatus}"
									style=""></label></td>
							</c:when>
							<c:otherwise>
								<td><label title="${productCompareDTO[3].stockStatus}"
									style=""></label> </br> <input type="submit" class="orng_btn"
									value="ADD TO CART" /></td>
							</c:otherwise>
						</c:choose>
					</c:if>
				</tr>
				<!-- <tr>
					<td>Availability</td>
					<td><b>Out of Stock</b></br> This item is Out of Stock</td>
					<td>This item is in Stock </br> <input type="submit"
						class="orng_btn" value="ADD TO CART" />
					</td>

				</tr>
				<tr>
					<td colspan="3"><div class="searchable_spec">In the box</div></td>

				</tr>
				<tr>
					<td>Color</td>
					<td>White and blue</td>
					<td>Green</td>

				</tr>
				<tr>
					<td>Part Number</td>
					<td>GC1905</td>
					<td>GC1010</td>

				</tr>
				<tr>
					<td colspan="3"><div class="searchable_spec">Warranty</div></td>
				</tr>
				<tr>
					<td>Domestic Term</td>
					<td>2 Years</td>
					<td>2 Years</td>

				</tr> -->
			</tbody>
		</table>
	</div>







	<!-- Compare Popup Ends-->

</div>


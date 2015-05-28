<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%--  <c:forEach items="${fields}" var="field" varStatus="count">

	<tr>
		<c:set var="fieldName" value="${field.name}"></c:set>
		<c:set var="fieldValue" value="${productCompareDTO[0]}.${fieldName}"/>
		<td>${fieldName }</td>

		<td>${productCompareDTO[0][field.name]}</td>
	</tr>

</c:forEach>
 --%>


<c:url var="addToCartUrl" value="/cart/add"></c:url>

<div id="light" class="white_content2">
	<h1>
		Compare <span>${productCompareDTO[0].name}</span>
	</h1>

	<p>You can add upto 4 Product to compare</p>
	<div class="compare_product">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>&nbsp;</td>
				<td width="25%"><label title="${productCompareDTO[0].name}"></label>

					<div class="prdct_comp">
						<img src="${productCompareDTO[0].media.URL}" />
					</div></td>
				<td width="25%"><label title="${productCompareDTO[1].name}"></label>

					<div class="prdct_comp">
						<img src="${productCompareDTO[1].media.URL}">
					</div></td>
				<c:if test="${not empty productCompareDTO[2] }">
					<td width="25%"><label title="${productCompareDTO[2].name}"></label>

						<div class="prdct_comp">
							<img src="${productCompareDTO[2].media.URL}" />
						</div></td>

				</c:if>
				<c:if test="${not empty productCompareDTO[3]}">
					<td width="25%"><label title="${productCompareDTO[3].name}"></label>

						<div class="prdct_comp">
							<img src="${productCompareDTO[3].media.URL}" />
						</div></td>
				</c:if>
			</tr>
			<c:forEach items="${fields}" var="field" varStatus="count">
				<c:if
					test="${field.name ne 'stockStatus' && field.name ne 'quantity' &&  field.name ne 'media'}">
					<tr>
						<td>${field.name}</td>
						<td><span class="${field.name}">${productCompareDTO[0][field.name]}</span></td>
						<c:if test="${not empty productCompareDTO[1]}">
							<td><span class="${field.name}">
									${productCompareDTO[1][field.name]}</span></td>
						</c:if>
						<c:if test="${not empty productCompareDTO[2]}">
							<td><span> ${productCompareDTO[2][field.name]}</span></td>
						</c:if>
						<c:if test="${not empty productCompareDTO[3]}">
							<td><span>${productCompareDTO[3][field.name]}</span></td>
						</c:if>
						<!-- <td>&nbsp;</td> -->
					</tr>
				</c:if>
			</c:forEach>

			<tr>
				<td>Availability</td>
				<c:choose>
					<c:when
						test="${productCompareDTO[0].quantity eq 0 || null== productCompareDTO[0].quantity}">
					${productCompareDTO[0].stockStatus}
						<td><label title="${productCompareDTO[0].stockStatus}"
							style="color: green">Out Of Stock</label></td>
					</c:when>
					<c:otherwise>
						<td>
							<form id="addToCartForm" class="add_to_cart_form"
								action="${addToCartUrl}" method="post">
								<input type="hidden" name="productCodePost"
									value="${productCompareDTO[0].code}">

								<button id="addToCartButton" type="submit"
									class="btn btn-primary btn-block js-add-to-cart">Add
									to bag</button>

								<!-- <input type="hidden" name="CSRFToken"
									value="0d0104fd-3d00-490d-a605-a23ac62e8602"> --> <input
									type="hidden" maxlength="3" size="1" id="qty" name="qty"
									class="qty js-qty-selector-input" value="1"> 
									<%-- <a
									href="${addToCartUrl}" class="orng_btn" title="ADD TO CART">ADD
									TO CART</a> --%>
							</form>
						</td>

					</c:otherwise>
				</c:choose>
				<c:if test="${not empty productCompareDTO[1]}">
					<c:choose>
						<c:when
							test="${productCompareDTO[1].quantity eq 0 || null== productCompareDTO[1].quantity}">
							<td><label title="${productCompareDTO[1].stockStatus}"
								style="color: green">Out Of Stock</label></td>
						</c:when>
						<c:otherwise>
							<td>
								<form id="addToCartForm" class="add_to_cart_form"
									action="${addToCartUrl}" method="post">
									<input type="hidden" name="productCodePost"
										value="${productCompareDTO[1].code}">

									<button id="addToCartButton" type="submit"
										class="btn btn-primary btn-block js-add-to-cart">Add
										to bag</button>

									<!-- <input type="hidden" name="CSRFToken"
										value="0d0104fd-3d00-490d-a605-a23ac62e8602"> -->

								</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if
					test="${not empty productCompareDTO[2]}">
					<c:choose>
						<c:when test="${productCompareDTO[2].quantity eq 0 || null== productCompareDTO[2].quantity}">
							<td><label title="${productCompareDTO[2].stockStatus}"
								style="">Out Of Stock</label></td>
						</c:when>
						<c:otherwise>
							<td>
								<form id="addToCartForm" class="add_to_cart_form"
									action="${addToCartUrl}" method="post">
									<input type="hidden" name="productCodePost"
										value="${productCompareDTO[2].code}">

									<button id="addToCartButton" type="submit"
										class="btn btn-primary btn-block js-add-to-cart">Add
										to bag</button>

									<!-- <input type="hidden" name="CSRFToken"
										value="0d0104fd-3d00-490d-a605-a23ac62e8602">
 -->
								</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${not empty productCompareDTO[3]}">
					<c:choose>
						<c:when test="${productCompareDTO[3].quantity eq 0  || null== productCompareDTO[3].quantity}">
							<td><label title="${productCompareDTO[3].stockStatus}"
								style="color: green">${productCompareDTO[3].stockStatus}</label></td>
						</c:when>
						<c:otherwise>
							<td><form id="addToCartForm" class="add_to_cart_form"
									action="${addToCartUrl}" method="post">
									<input type="hidden" name="productCodePost" value="${productCompareDTO[3].code}">

									<button id="addToCartButton" type="submit"
										class="btn btn-primary btn-block js-add-to-cart">Add
										to bag</button>

								<!-- 	<input type="hidden" name="CSRFToken"
										value="0d0104fd-3d00-490d-a605-a23ac62e8602"> -->
								</form></td> 
						</c:otherwise>
					</c:choose>
				</c:if>
			</tr>
		</table>
	</div>
</div>


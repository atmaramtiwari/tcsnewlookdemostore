/**
 *
 */
package com.tcs.storefront.controllers.cms;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcs.core.services.impl.DefaultProductCompareService;
import com.tcs.facades.product.compare.data.ProductCompareData;
import com.tcs.newlook.model.ProductCompareComponentModel;
import com.tcs.storefront.controllers.ControllerConstants;


/**
 * @author 898970
 *
 */

@Controller("ProductCompareComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.ProductCompareComponent)
public class ProductCompareComponentController extends AbstractCMSComponentController<ProductCompareComponentModel>
{


	private static final Logger LOG = Logger.getLogger(ProductCompareComponentController.class);

	@Autowired
	private DefaultProductCompareService productCompareService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.storefront.controllers.cms.AbstractCMSComponentController#fillModel(javax.servlet.http.HttpServletRequest,
	 * org.springframework.ui.Model, de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ProductCompareComponentModel component)
	{
		final String productId = request.getParameter("productId");
		if (StringUtils.isNotEmpty(productId.trim()))
		{
			final List<ProductCompareData> productCompareDTO = productCompareService.findProductDetailByCode(productId.trim());
			final Field[] fields = ProductCompareData.class.getDeclaredFields();
			model.addAttribute("fields", fields);
			LOG.info("products=" + productCompareDTO);
			model.addAttribute("productCompareDTO", productCompareDTO);
		}



	}

}

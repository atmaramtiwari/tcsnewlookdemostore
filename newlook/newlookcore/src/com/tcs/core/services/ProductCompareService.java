/**
 *
 */
package com.tcs.core.services;

import de.hybris.platform.product.ProductService;

import java.util.List;

import com.tcs.facades.product.compare.data.ProductCompareData;


/**
 * @author 898970
 *
 */
public interface ProductCompareService extends ProductService
{

	List<ProductCompareData> findProductDetailByCode(String codes);
}

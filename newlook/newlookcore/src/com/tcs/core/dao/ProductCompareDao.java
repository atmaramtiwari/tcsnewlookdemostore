/**
 *
 */
package com.tcs.core.dao;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;

import java.util.List;


/**
 * @author 898970
 *
 */
public interface ProductCompareDao extends ProductDao
{
	List<ProductModel> findProductDetailByCode(final String codes);
}

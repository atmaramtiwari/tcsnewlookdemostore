/**
 *
 */
package com.tcs.core.converters;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.tcs.facades.product.compare.data.ProductCompareData;


/**
 * @author 898970
 *
 */
public interface ProductCompareConverter extends Converter<ProductModel, ProductCompareData>
{
	ProductCompareData convert(ProductModel paramSOURCE, Integer stockLevel) throws ConversionException;

}

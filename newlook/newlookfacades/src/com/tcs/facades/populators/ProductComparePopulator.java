/**
 *
 */
package com.tcs.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.tcs.facades.product.compare.data.ProductCompareData;


/**
 * @author 898970
 *
 */
public class ProductComparePopulator implements Populator<ProductModel, ProductCompareData>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductModel source, final ProductCompareData target) throws ConversionException
	{
		target.setName(source.getName());
		target.setCode(source.getCode());
		target.setEan(source.getEan());
		target.setMedia(source.getThumbnail());
		target.setAverageRating(source.getAverageRating());
		target.setDeliveryTime(String.valueOf(source.getDeliveryTime()));
		target.setManufactureAid(source.getManufacturerAID());
		target.setManufacturerName(source.getManufacturerName());
		for (final PriceRowModel price : source.getEurope1Prices())
		{
			target.setPrice(price.getCurrency().getSymbol() + " " + String.valueOf(price.getPrice()));
			//target.setCurrency(String.valueOf(price.getCurrency()));
			break;
		}
	}

}

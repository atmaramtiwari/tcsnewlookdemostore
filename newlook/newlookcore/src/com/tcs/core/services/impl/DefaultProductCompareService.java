/**
 *
 */
package com.tcs.core.services.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSSiteService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.media.impl.DefaultMediaService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.stock.impl.DefaultStockService;
import de.hybris.platform.util.WeakArrayList;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tcs.core.dao.impl.DefaultProductCompareDao;
import com.tcs.core.services.ProductCompareService;
import com.tcs.facades.product.compare.data.ProductCompareData;


/**
 * @author 898970
 *
 */
@SuppressWarnings("deprecation")
public class DefaultProductCompareService extends AbstractBusinessService implements ProductCompareService
{
	private static final Logger LOG = Logger.getLogger(DefaultProductCompareService.class);

	@Autowired
	private Converter<ProductModel, ProductCompareData> productCompareConverter;

	@Autowired
	private DefaultMediaService mediaService;

	@Autowired
	private DefaultCMSSiteService cmsSiteService;
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getProduct(java.lang.String)
	 */

	@Autowired
	private DefaultProductCompareDao productCompareDao;

	@Autowired
	private DefaultStockService stockService;


	@Override
	public ProductModel getProduct(final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getProductForCode(java.lang.String)
	 */
	@Override
	public ProductModel getProductForCode(final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getProduct(de.hybris.platform.catalog.model.CatalogVersionModel,
	 * java.lang.String)
	 */
	@Override
	public ProductModel getProduct(final CatalogVersionModel catalogVersion, final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.ProductService#getOrderableUnit(de.hybris.platform.core.model.product.ProductModel)
	 */

	@Override
	public UnitModel getOrderableUnit(final ProductModel product)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getUnit(java.lang.String)
	 */
	@Override
	public UnitModel getUnit(final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getProducts(de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public List<ProductModel> getProducts(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.ProductService#getProductsForCategory(de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public List<ProductModel> getProductsForCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.ProductService#getOnlineProductsForCategory(de.hybris.platform.category.model.CategoryModel
	 * )
	 */
	@Override
	public List<ProductModel> getOnlineProductsForCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getOfflineProductsForCategory(de.hybris.platform.category.model.
	 * CategoryModel)
	 */
	@Override
	public List<ProductModel> getOfflineProductsForCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getProducts(de.hybris.platform.category.model.CategoryModel, int,
	 * int)
	 */
	@Override
	public SearchResult<ProductModel> getProducts(final CategoryModel category, final int start, final int count)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.ProductService#getProductsForCategory(de.hybris.platform.category.model.CategoryModel,
	 * int, int)
	 */
	@Override
	public List<ProductModel> getProductsForCategory(final CategoryModel category, final int start, final int count)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.ProductService#getAllProductsCountForCategory(de.hybris.platform.category.model.
	 * CategoryModel)
	 */
	@Override
	public Integer getAllProductsCountForCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.ProductService#containsProductsForCategory(de.hybris.platform.category.model.CategoryModel
	 * )
	 */
	@Override
	public boolean containsProductsForCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.ProductService#getProductForCode(de.hybris.platform.catalog.model.CatalogVersionModel,
	 * java.lang.String)
	 */
	@Override
	public ProductModel getProductForCode(final CatalogVersionModel catalogVersion, final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}


	/**
	 * Find product detail by code1.
	 *
	 * @param codes
	 *           the codes
	 * @return the list
	 */
	@Override
	public List<ProductCompareData> findProductDetailByCode(final String codes)
	{




		if (StringUtils.isNotEmpty(codes))
		{
			final List<ProductModel> products = productCompareDao.findProductDetailByCode(codes);
			final List<ProductCompareData> productCompareData = getProductCompareData(products);
			return productCompareData;
		}
		else
		{
			LOG.info("DefaultProductCompareService.findProductDetailByCode():There are no codes");
			return null;
		}

	}

	/**
	 * Gets the product compare data.
	 *
	 * @param products
	 *           the products
	 * @return the product compare data
	 */
	private List<ProductCompareData> getProductCompareData(final List<ProductModel> products)
	{
		final List<ProductCompareData> productCompareDataList = new WeakArrayList<ProductCompareData>();
		for (final ProductModel product : products)
		{
			final ProductCompareData productCompareData = productCompareConverter.convert(product);
			try
			{
				productCompareData.setMedia(product.getThumbnail());
				productCompareData.setStockStatus(StockLevelStatus.OUTOFSTOCK);
				final int totalStock = stockService.getTotalStockLevelAmount(product);

				//cmsSiteService.getCurrentSite().get
				if (totalStock == 0)
				{
					LOG.info("the product with code:" + product.getCode() + " is out of stock");
				}
				else
				{
					productCompareData.setQuantity(Integer.valueOf(totalStock));
					productCompareData.setStockStatus(StockLevelStatus.INSTOCK);
					LOG.info("the product with code:" + product.getCode() + " has a quantity of:" + totalStock);
				}

			}
			catch (final StockLevelNotFoundException exception)
			{
				LOG.error("no stock level for product:" + product.getCode());
			}
			productCompareDataList.add(productCompareData);
		}

		LOG.info("Size of productCompareDataList:" + productCompareDataList.size());

		return productCompareDataList;
	}
}

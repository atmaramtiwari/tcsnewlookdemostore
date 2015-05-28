/**
 *
 */
package com.tcs.core.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.impl.DefaultCatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.impl.DefaultFlexibleSearchService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tcs.core.dao.ProductCompareDao;


/**
 * @author 898970
 *
 */
public class DefaultProductCompareDao extends DefaultGenericDao<ProductModel> implements ProductCompareDao
{

	private static final Logger LOG = Logger.getLogger(DefaultProductCompareDao.class);

	@Autowired
	private DefaultFlexibleSearchService flexibleSearchService;

	@Autowired
	private DefaultCatalogVersionService catalogVersionService;

	/**
	 * @param typecode
	 */
	public DefaultProductCompareDao(final String typecode)
	{
		super(typecode);
		// YTODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.daos.ProductDao#findProductsByCategory(de.hybris.platform.category.model.CategoryModel,
	 * int, int)
	 */
	@Override
	public SearchResult<ProductModel> findProductsByCategory(final CategoryModel category, final int start, final int count)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.daos.ProductDao#findProducts(de.hybris.platform.category.model.CategoryModel,
	 * boolean)
	 */
	@Override
	public List<ProductModel> findProducts(final CategoryModel category, final boolean online)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.daos.ProductDao#findOnlineProductsByCategory(de.hybris.platform.category.model.
	 * CategoryModel)
	 */
	@Override
	public List<ProductModel> findOnlineProductsByCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.daos.ProductDao#findOfflineProductsByCategory(de.hybris.platform.category.model.
	 * CategoryModel)
	 */
	@Override
	public List<ProductModel> findOfflineProductsByCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.daos.ProductDao#findProductsByCode(java.lang.String)
	 */
	@Override
	public List<ProductModel> findProductsByCode(final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.product.daos.ProductDao#findProductsByCode(de.hybris.platform.catalog.model.CatalogVersionModel
	 * , java.lang.String)
	 */
	@Override
	public List<ProductModel> findProductsByCode(final CatalogVersionModel catalogVersion, final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.product.daos.ProductDao#findAllProductsCountByCategory(de.hybris.platform.category.model.
	 * CategoryModel)
	 */
	@Override
	public Integer findAllProductsCountByCategory(final CategoryModel category)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.tcs.core.dao.ProductCompareDao#findProductDetailByCode(java.lang.String)
	 */
	@Override
	public List<ProductModel> findProductDetailByCode(final String codes)
	{
		validateParameterNotNull(codes, "Product code must not be null!");
		final StringBuilder query = new StringBuilder();

		final List<String> codeList = Arrays.asList(codes.split(","));

		query.append(" select {" + ProductModel.PK + "} from {").append(ProductModel._TYPECODE).append(" as product")
				.append("  JOIN ").append(CatalogModel._TYPECODE).append(" as catalog on {product.catalog}={catalog.")
				.append(CatalogModel.PK).append("}").append(" JOIN ").append(CatalogVersionModel._TYPECODE)
				.append(" as catVersion on {product.").append(ProductModel.CATALOGVERSION).append("}={catVersion.pk}}")
				.append(" where {product.code}").append(" IN (?codes) and {catalog.id}=?catalog and {catVersion.version}='Online'");

		//query.append("select {pk} from {Product as product  JOIN Catalog as catalog on {product.catalog}={catalog.pk} JOIN CatalogVersion as catVersion on {product.catalogversion}={catVersion.pk}} where {product.code}  IN (?codes) and {catalog.id}='apparelProductCatalog' and {catVersion.version}='Online'");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);

		final Map<String, Object> parameterMap = new WeakHashMap<String, Object>();
		parameterMap.put("codes", codeList);
		parameterMap.put("catalog", "apparelProductCatalog");

		searchQuery.addQueryParameters(parameterMap);
		LOG.info("Product Compare Query:" + searchQuery);

		final SearchResult<ProductModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();
	}


}

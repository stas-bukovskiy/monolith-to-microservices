package app.product.repo;

import app.common.search.PageSearchResult;
import app.product.model.Product;
import app.product.search.ProductSearchCriteria;

public interface ProductRepositoryCustom {

    PageSearchResult<Product> search(ProductSearchCriteria criteria);
}

package warehouse.repo;

import warehouse.common.search.PageSearchResult;
import warehouse.model.Product;
import warehouse.search.ProductSearchCriteria;

public interface ProductRepositoryCustom {

    PageSearchResult<Product> search(ProductSearchCriteria criteria);
}

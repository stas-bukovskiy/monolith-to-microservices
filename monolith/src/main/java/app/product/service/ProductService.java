package app.product.service;

import app.common.search.PageSearchResult;
import app.product.dto.ProductDto;
import app.product.search.ProductSearchCriteria;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);

    PageSearchResult<ProductDto> search(ProductSearchCriteria criteria);
}

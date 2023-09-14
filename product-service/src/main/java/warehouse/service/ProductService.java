package warehouse.service;

import warehouse.common.search.PageSearchResult;
import warehouse.dto.ProductDto;
import warehouse.search.ProductSearchCriteria;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);

    PageSearchResult<ProductDto> search(ProductSearchCriteria criteria);
}

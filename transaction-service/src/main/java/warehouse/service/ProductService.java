package warehouse.service;

import warehouse.dto.ProductDto;

public interface ProductService {
    ProductDto findById(Long id);
}

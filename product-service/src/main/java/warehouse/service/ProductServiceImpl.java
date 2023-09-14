package warehouse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.common.exception.ResourceNotFoundException;
import warehouse.common.search.PageSearchResult;
import warehouse.dto.ProductDto;
import warehouse.model.Product;
import warehouse.repo.ProductRepository;
import warehouse.search.ProductSearchCriteria;
import warehouse.validations.ProductDtoValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoValidator productDtoValidator;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductDtoValidator productDtoValidator) {
        this.productRepository = productRepository;
        this.productDtoValidator = productDtoValidator;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = this.productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new ProductDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return this.productRepository
                .findAll()
                .stream()
                .map(product -> new ProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        productDtoValidator.validate(productDto);
        Product product = this.dtoToEntity(productDto);
        Product savedProduct = this.productRepository.save(product);
        return new ProductDto(savedProduct);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public PageSearchResult<ProductDto> search(ProductSearchCriteria criteria) {
        PageSearchResult<Product> page = this.productRepository.search(criteria);
        List<ProductDto> dtos = page
                .getPageData()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());

        return new PageSearchResult<>(page.getTotalRows(), dtos);
    }

    private Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}

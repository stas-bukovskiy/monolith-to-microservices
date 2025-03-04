package warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import warehouse.common.search.PageSearchResult;
import warehouse.common.search.SearchRequest;
import warehouse.common.utils.SearchUtils;
import warehouse.dto.ProductDto;
import warehouse.search.ProductSearchCriteria;
import warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ProductDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return this.productService.save(productDto);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return this.productService.save(productDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
    }

    @PostMapping("search")
    public Page<ProductDto> search(@RequestBody SearchRequest searchRequest) {
        ProductSearchCriteria criteria = SearchUtils.createSearchCriteria(searchRequest, ProductSearchCriteria.class);
        PageSearchResult<ProductDto> pageSearchResult = this.productService.search(criteria);
        return SearchUtils.pageOf(searchRequest, pageSearchResult);
    }

}

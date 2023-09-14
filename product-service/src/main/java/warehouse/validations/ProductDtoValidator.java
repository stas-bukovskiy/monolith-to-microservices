package warehouse.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warehouse.common.validation.BaseValidator;
import warehouse.common.validation.CommonValidatorUtils;
import warehouse.dto.ProductDto;
import warehouse.model.Product;
import warehouse.repo.ProductRepository;

@Component
public class ProductDtoValidator implements BaseValidator<ProductDto> {

    private static final String FIELD_CODE = "code";

    private final ProductRepository productRepository;
    private final CommonValidatorUtils<ProductDto, Product> commonValidatorUtils;

    @Autowired
    public ProductDtoValidator(
            ProductRepository productRepository,
            CommonValidatorUtils<ProductDto, Product> commonValidatorUtils) {
        this.productRepository = productRepository;
        this.commonValidatorUtils = commonValidatorUtils;
    }

    public void validate(ProductDto obj) {
        validateUniqueCode(obj);
    }

    private void validateUniqueCode(ProductDto productDto) {

        Product product = this.productRepository
                .findByCode(productDto.getCode())
                .orElse(null);

        // Found product with the given code
        if (product != null) {
            commonValidatorUtils.validateUniqueField(FIELD_CODE, productDto, product);
        }

    }
}

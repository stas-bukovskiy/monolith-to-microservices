package warehouse.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warehouse.common.validation.BaseValidator;
import warehouse.common.validation.CommonValidatorUtils;
import warehouse.dto.WareTransactionDetailDto;

@Component
public class WareTransactionDetailDtoValidator implements BaseValidator<WareTransactionDetailDto> {

    private static final String FIELD_PRODUCT_ID = "product";
    private static final String FIELD_SHELF_ID = "shelf";
    private static final String FIELD_QUANTITY = "quantity";

    private final CommonValidatorUtils commonValidatorUtils;

    @Autowired
    public WareTransactionDetailDtoValidator(CommonValidatorUtils commonValidatorUtils) {
        this.commonValidatorUtils = commonValidatorUtils;
    }

    @Override
    public void validate(WareTransactionDetailDto obj) {
        this.commonValidatorUtils.validateRequiredField(FIELD_PRODUCT_ID, obj.getProductId());
        this.commonValidatorUtils.validateRequiredField(FIELD_SHELF_ID, obj.getShelfId());
        this.commonValidatorUtils.validateRequiredField(FIELD_QUANTITY, obj.getQuantity());
    }
}

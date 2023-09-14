package warehouse.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warehouse.common.validation.BaseValidator;
import warehouse.common.validation.CommonValidatorUtils;
import warehouse.dto.StockClerkDto;
import warehouse.repo.StockClerkRepository;

@Component
public class StockClerkDtoValidator implements BaseValidator<StockClerkDto> {

    private static final String FIELD_FIRST_NAME = "First name";
    private static final String FIELD_LAST_NAME = "Last name";
    private static final String FIELD_REGISTRY_NUMBER = "Registry number";

    private final StockClerkRepository stockClerkRepository;
    private final CommonValidatorUtils commonValidatorUtils;

    @Autowired
    public StockClerkDtoValidator(
            StockClerkRepository stockClerkRepository,
            CommonValidatorUtils commonValidatorUtils) {
        this.stockClerkRepository = stockClerkRepository;
        this.commonValidatorUtils = commonValidatorUtils;
    }

    @Override
    public void validate(StockClerkDto obj) {
        validateUniqueRegistryNumber(obj);
        this.commonValidatorUtils.validateRequiredField(FIELD_FIRST_NAME, obj.getFirstName());
        this.commonValidatorUtils.validateRequiredField(FIELD_LAST_NAME, obj.getLastName());
    }

    private void validateUniqueRegistryNumber(StockClerkDto stockClerkDto) {
        this.stockClerkRepository
                .findByRegistryNumber(stockClerkDto.getRegistryNumber()).ifPresent(stockClerk -> this.commonValidatorUtils.validateUniqueField(FIELD_REGISTRY_NUMBER, stockClerkDto, stockClerk));

    }
}

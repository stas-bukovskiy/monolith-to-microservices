package warehouse.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warehouse.common.validation.BaseValidator;
import warehouse.common.validation.CommonValidatorUtils;
import warehouse.dto.ShelfDto;
import warehouse.model.Shelf;
import warehouse.repo.ShelfRepository;

@Component
public class ShelfDtoValidator implements BaseValidator<ShelfDto> {

    private static final String FIELD_CODE = "code";

    private final ShelfRepository shelfRepository;
    private final CommonValidatorUtils<ShelfDto, Shelf> commonValidatorUtils;

    @Autowired
    public ShelfDtoValidator(ShelfRepository shelfRepository, CommonValidatorUtils<ShelfDto, Shelf> commonValidatorUtils) {
        this.shelfRepository = shelfRepository;
        this.commonValidatorUtils = commonValidatorUtils;
    }

    @Override
    public void validate(ShelfDto obj) {
        validateUniqueCode(obj);
    }

    private void validateUniqueCode(ShelfDto shelfDto) {
        Shelf shelf = this.shelfRepository
                .findByCode(shelfDto.getCode())
                .orElse(null);

        if (shelf != null) {
            this.commonValidatorUtils.validateUniqueField(FIELD_CODE, shelfDto, shelf);
        }
    }

}

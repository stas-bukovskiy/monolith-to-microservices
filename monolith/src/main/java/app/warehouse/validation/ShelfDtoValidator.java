package app.warehouse.validation;

import app.common.validation.BaseValidator;
import app.common.validation.CommonValidatorUtils;
import app.warehouse.dto.ShelfDto;
import app.warehouse.model.Shelf;
import app.warehouse.repo.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        if(shelf != null) {
            this.commonValidatorUtils.validateUniqueField(FIELD_CODE, shelfDto, shelf);
        }
    }

}

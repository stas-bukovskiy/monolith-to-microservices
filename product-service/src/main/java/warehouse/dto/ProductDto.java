package warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import warehouse.enums.MeasurementUnit;
import warehouse.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String code;
    private String description;
    private MeasurementUnit measurementUnit;

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this);
    }
}

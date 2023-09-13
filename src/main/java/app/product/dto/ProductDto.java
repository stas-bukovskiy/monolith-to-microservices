package app.product.dto;

import app.product.enums.MeasurementUnit;
import app.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

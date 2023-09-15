package warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import warehouse.enums.MeasurementUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String code;
    private String description;
    private MeasurementUnit measurementUnit;
}

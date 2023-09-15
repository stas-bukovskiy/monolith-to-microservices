package warehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ShelfDto {
    private Long id;
    private String code;
    private Long warehouseId;
}

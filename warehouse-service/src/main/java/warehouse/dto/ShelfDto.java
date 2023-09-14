package warehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import warehouse.model.Shelf;
import warehouse.model.Warehouse;

@NoArgsConstructor
@Data
public class ShelfDto {

    private Long id;
    private String code;
    private Long warehouseId;

    public ShelfDto(Shelf shelf) {
        BeanUtils.copyProperties(shelf, this, "warehouseId");

        Warehouse warehouse = shelf.getWarehouse();
        if (warehouse != null) {
            this.warehouseId = warehouse.getId();
        }
    }
}

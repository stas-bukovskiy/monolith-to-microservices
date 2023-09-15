package warehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import warehouse.model.WareTransactionDetail;

@Data
@NoArgsConstructor
public class WareTransactionDetailDto {

    private Long id;
    private Long wareTransactionId;
    private Long productId;
    private Long shelfId;
    private Long quantity;

    public WareTransactionDetailDto(WareTransactionDetail wareTransactionDetails) {
        BeanUtils.copyProperties(wareTransactionDetails, this);
    }
}

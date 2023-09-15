package warehouse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import warehouse.enums.WareTransactionType;
import warehouse.model.WareTransaction;
import warehouse.model.WareTransactionDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WareTransactionDto {

    private Long id;
    private WareTransactionType wareTransactionType;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;

    private Long stockClerkId;
    private List<WareTransactionDetailDto> wareTransactionDetails = new ArrayList<>();

    public WareTransactionDto(WareTransaction wareTransaction) {
        BeanUtils.copyProperties(wareTransaction, this, "wareTransactionDetails");

        List<WareTransactionDetail> wareTransactionDetails = wareTransaction.getWareTransactionDetails();
        if (wareTransactionDetails != null && wareTransactionDetails.size() > 0) {
            wareTransactionDetails.forEach(wTxDetails -> {
                this.wareTransactionDetails.add(new WareTransactionDetailDto(wTxDetails));
            });
        }
    }

}

package app.transaction.dto;

import app.product.model.Product;
import app.transaction.model.WareTransaction;
import app.transaction.model.WareTransactionDetail;
import app.warehouse.model.Shelf;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

        WareTransaction wareTransaction = wareTransactionDetails.getWareTransaction();
        if (wareTransaction != null) {
            this.wareTransactionId = wareTransaction.getId();
        }

        Product product = wareTransactionDetails.getProduct();
        if (product != null) {
            this.productId = product.getId();
        }

        Shelf shelf = wareTransactionDetails.getShelf();
        if (shelf != null) {
            this.shelfId = shelf.getId();
        }
    }
}

package warehouse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Detail entity for massive ware import/export
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_ware_transaction_detail")
public class WareTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ware_transaction_id")
    private WareTransaction wareTransaction;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "shelf_id")
    private Long shelfId;

    @Column(name = "quantity")
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WareTransactionDetail that = (WareTransactionDetail) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

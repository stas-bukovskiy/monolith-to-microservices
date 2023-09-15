package warehouse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import warehouse.enums.WareTransactionType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Master entity for massive ware import/export
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_ware_transaction")
public class WareTransaction {

    @OneToMany(mappedBy = "wareTransaction",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    List<WareTransactionDetail> wareTransactionDetails;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "ware_transaction_type")
    private WareTransactionType wareTransactionType;
    @Column(name = "description")
    private String description;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    @Column(name = "stock_clerk_id")
    private Long stockClerkId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WareTransaction that = (WareTransaction) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

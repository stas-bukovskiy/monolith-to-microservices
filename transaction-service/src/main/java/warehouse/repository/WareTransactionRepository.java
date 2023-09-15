package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.WareTransaction;

@Repository
public interface WareTransactionRepository extends JpaRepository<WareTransaction, Long> {
}

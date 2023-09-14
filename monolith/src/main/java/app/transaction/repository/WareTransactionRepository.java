package app.transaction.repository;

import app.transaction.model.WareTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareTransactionRepository extends JpaRepository<WareTransaction, Long> {
}

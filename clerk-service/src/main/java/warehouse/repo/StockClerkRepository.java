package warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.StockClerk;

import java.util.Optional;

@Repository
public interface StockClerkRepository extends JpaRepository<StockClerk, Long> {

    Optional<StockClerk> findByRegistryNumber(String registryNumber);
}

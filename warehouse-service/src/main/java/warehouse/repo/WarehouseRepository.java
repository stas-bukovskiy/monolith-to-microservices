package warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}

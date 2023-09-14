package warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.Shelf;

import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    Optional<Shelf> findByCode(String code);
}

package Backend.HIFI.domain.store.repository;

import Backend.HIFI.domain.store.entity.Category;
import Backend.HIFI.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long id);

    List<Store> findAllByCategory(Category category);
}

package uz.zazu.king.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.inventory.entity.InventoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, String> {

    @Query(value = "SELECT * FROM inventories WHERE is_active = true", nativeQuery = true)
    List<InventoryEntity> findAllActive();

    @Query(value = "SELECT * FROM inventories WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<InventoryEntity> findActiveById(String id);
}
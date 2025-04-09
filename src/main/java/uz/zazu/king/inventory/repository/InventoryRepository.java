package uz.zazu.king.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.inventory.entity.InventoryEntity;

import java.util.List;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryEntity, String> {

    @Query("{ 'isActive': true }")
    List<InventoryEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    InventoryEntity findActiveById(String id);
}
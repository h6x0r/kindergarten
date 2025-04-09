package uz.zazu.king.lead.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.lead.entity.ChildEntity;

import java.util.List;

@Repository
public interface ChildRepository extends MongoRepository<ChildEntity, String> {

    @Query("{ 'isActive': true }")
    List<ChildEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    ChildEntity findActiveById(String id);
}
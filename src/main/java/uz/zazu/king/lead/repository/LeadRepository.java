package uz.zazu.king.lead.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.lead.entity.LeadEntity;

import java.util.List;

@Repository
public interface LeadRepository extends MongoRepository<LeadEntity, String> {

    @Query("{ 'isActive': true }")
    List<LeadEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    LeadEntity findActiveById(String id);
}
package uz.zazu.king.lead.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.lead.entity.QuestionnaireEntity;

import java.util.List;

@Repository
public interface ChildRepository extends MongoRepository<QuestionnaireEntity, String> {

    @Query("{ 'isActive': true }")
    List<QuestionnaireEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    QuestionnaireEntity findActiveById(String id);
}
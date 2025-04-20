package uz.zazu.king.questionnaire.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.questionnaire.entity.LeadQuestionnaireEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadQuestionnaireRepository extends MongoRepository<LeadQuestionnaireEntity, String> {

    @Query("{ 'isActive': true }")
    List<LeadQuestionnaireEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<LeadQuestionnaireEntity> findActiveById(String id);
}
package uz.zazu.king.questionnaire.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.zazu.king.questionnaire.entity.LeadExcursionQuestionnaire;

import java.util.List;
import java.util.Optional;

public interface LeadExcursionQuestionnaireRepository extends MongoRepository<LeadExcursionQuestionnaire, String> {

    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<LeadExcursionQuestionnaire> findActiveById(String id);

    @Query("{ 'isActive': true }")
    List<LeadExcursionQuestionnaire> findAllActive();
}
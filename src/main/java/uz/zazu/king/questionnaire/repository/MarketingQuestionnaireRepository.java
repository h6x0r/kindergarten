package uz.zazu.king.questionnaire.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.zazu.king.questionnaire.entity.MarketingQuestionnaire;

import java.util.List;
import java.util.Optional;

public interface MarketingQuestionnaireRepository extends MongoRepository<MarketingQuestionnaire, String> {

    @Query("{'_id': ?0, 'isActive': true}")
    Optional<MarketingQuestionnaire> findActiveById(String id);

    @Query("{'isActive': true}")
    List<MarketingQuestionnaire> findAllActive();
}
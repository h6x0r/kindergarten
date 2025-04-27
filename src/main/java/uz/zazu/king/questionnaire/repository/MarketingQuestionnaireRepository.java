package uz.zazu.king.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.zazu.king.questionnaire.entity.MarketingQuestionnaire;

import java.util.List;
import java.util.Optional;

public interface MarketingQuestionnaireRepository extends JpaRepository<MarketingQuestionnaire, String> {

    @Query(value = "SELECT * FROM marketing_questionnaires WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<MarketingQuestionnaire> findActiveById(String id);

    @Query(value = "SELECT * FROM marketing_questionnaires WHERE is_active = true", nativeQuery = true)
    List<MarketingQuestionnaire> findAllActive();
}
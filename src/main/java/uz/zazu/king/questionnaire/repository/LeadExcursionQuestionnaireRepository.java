package uz.zazu.king.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.zazu.king.questionnaire.entity.LeadExcursionQuestionnaire;

import java.util.List;
import java.util.Optional;

public interface LeadExcursionQuestionnaireRepository extends JpaRepository<LeadExcursionQuestionnaire, String> {

    @Query(value = "SELECT * FROM lead_excursion_questionnaires WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<LeadExcursionQuestionnaire> findActiveById(String id);

    @Query(value = "SELECT * FROM lead_excursion_questionnaires WHERE is_active = true", nativeQuery = true)
    List<LeadExcursionQuestionnaire> findAllActive();
}
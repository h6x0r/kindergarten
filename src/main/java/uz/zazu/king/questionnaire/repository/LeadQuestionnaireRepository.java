package uz.zazu.king.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.questionnaire.entity.LeadQuestionnaireEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadQuestionnaireRepository extends JpaRepository<LeadQuestionnaireEntity, String> {

    @Query(value = "SELECT * FROM lead_questionnaires WHERE is_active = true", nativeQuery = true)
    List<LeadQuestionnaireEntity> findAllActive();

    @Query(value = "SELECT * FROM lead_questionnaires WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<LeadQuestionnaireEntity> findActiveById(String id);
}
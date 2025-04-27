package uz.zazu.king.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.questionnaire.entity.ParentChildQuestionnaire;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentChildQuestionnaireRepository extends JpaRepository<ParentChildQuestionnaire, String> {

    @Query(value = "SELECT * FROM parent_child_questionnaires WHERE is_active = true", nativeQuery = true)
    List<ParentChildQuestionnaire> findAllByIsActiveTrue();

    @Query(value = "SELECT * FROM parent_child_questionnaires WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<ParentChildQuestionnaire> findByIdAndIsActiveTrue(String id);
}
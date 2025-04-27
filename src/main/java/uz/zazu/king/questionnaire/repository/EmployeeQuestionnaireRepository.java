package uz.zazu.king.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.questionnaire.entity.EmployeeQuestionnaire;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeQuestionnaireRepository extends JpaRepository<EmployeeQuestionnaire, String> {

    @Query(value = "SELECT * FROM employee_questionnaires WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<EmployeeQuestionnaire> findByIdAndIsActiveTrue(String id);

    @Query(value = "SELECT * FROM employee_questionnaires WHERE is_active = true", nativeQuery = true)
    List<EmployeeQuestionnaire> findAllByIsActiveTrue();
}
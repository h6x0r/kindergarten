package uz.zazu.king.questionnaire.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.questionnaire.entity.EmployeeQuestionnaire;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeQuestionnaireRepository extends MongoRepository<EmployeeQuestionnaire, String> {

    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<EmployeeQuestionnaire> findByIdAndIsActiveTrue(String id);

    @Query("{ 'isActive': true }")
    List<EmployeeQuestionnaire> findAllByIsActiveTrue();
}
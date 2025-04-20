package uz.zazu.king.questionnaire.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.questionnaire.entity.ParentChildQuestionnaire;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentChildQuestionnaireRepository extends MongoRepository<ParentChildQuestionnaire, String> {

    @Query("{ 'isActive': true }")
    List<ParentChildQuestionnaire> findAllByIsActiveTrue();

    @Query("{ 'id': ?0, 'isActive': true }")
    Optional<ParentChildQuestionnaire> findByIdAndIsActiveTrue(String id);
}
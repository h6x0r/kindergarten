package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;

import java.util.List;

@Repository
public interface QuestionnaireEducativeRoleRepository extends MongoRepository<QuestionnaireEducativeRoleEntity, String> {
    @Query("{ '_id': ?0, 'isActive': true }")
    QuestionnaireEducativeRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<QuestionnaireEducativeRoleEntity> findAllByIsActiveTrue();

}
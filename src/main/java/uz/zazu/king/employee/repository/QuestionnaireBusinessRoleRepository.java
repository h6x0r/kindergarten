package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.QuestionnaireBusinessRoleEntity;

import java.util.List;

@Repository
public interface QuestionnaireBusinessRoleRepository extends MongoRepository<QuestionnaireBusinessRoleEntity, String> {

    @Query("{ '_id': ?0, 'isActive': true }")
    QuestionnaireBusinessRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<QuestionnaireBusinessRoleEntity> findAllByIsActiveTrue();

}
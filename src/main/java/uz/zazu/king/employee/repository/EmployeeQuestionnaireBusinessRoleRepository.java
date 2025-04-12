package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.EmployeeQuestionnaireBusinessRoleEntity;

import java.util.List;

@Repository
public interface EmployeeQuestionnaireBusinessRoleRepository extends MongoRepository<EmployeeQuestionnaireBusinessRoleEntity, String> {

    @Query("{ '_id': ?0, 'isActive': true }")
    EmployeeQuestionnaireBusinessRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<EmployeeQuestionnaireBusinessRoleEntity> findAllByIsActiveTrue();

}
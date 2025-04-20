package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {

    @Query("{ 'isActive': true }")
    List<EmployeeEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<EmployeeEntity> findActiveById(String id);
}
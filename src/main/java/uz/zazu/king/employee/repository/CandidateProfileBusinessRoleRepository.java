package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateProfileBusinessRoleEntity;

import java.util.List;

@Repository
public interface CandidateProfileBusinessRoleRepository extends MongoRepository<CandidateProfileBusinessRoleEntity, String> {

    @Query("{ '_id': ?0, 'isActive': true }")
    CandidateProfileBusinessRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<CandidateProfileBusinessRoleEntity> findAllByIsActiveTrue();

}
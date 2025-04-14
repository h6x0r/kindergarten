package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateProfileEducatorRoleEntity;

import java.util.List;

@Repository
public interface CandidateProfileEducatorRoleRepository extends MongoRepository<CandidateProfileEducatorRoleEntity, String> {
    @Query("{ '_id': ?0, 'isActive': true }")
    CandidateProfileEducatorRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<CandidateProfileEducatorRoleEntity> findAllByIsActiveTrue();

}
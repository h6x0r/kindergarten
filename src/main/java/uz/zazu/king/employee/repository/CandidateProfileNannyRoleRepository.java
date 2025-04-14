package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateProfileNannyRoleEntity;

import java.util.List;

@Repository
public interface CandidateProfileNannyRoleRepository extends MongoRepository<CandidateProfileNannyRoleEntity, String> {
    @Query("{ '_id': ?0, 'isActive': true }")
    CandidateProfileNannyRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<CandidateProfileNannyRoleEntity> findAllByIsActiveTrue();

}
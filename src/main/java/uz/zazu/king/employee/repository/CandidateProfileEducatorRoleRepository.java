package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateApplicationEducatorRoleEntity;
import uz.zazu.king.employee.entity.CandidateApplicationNannyRoleEntity;

import java.util.List;

@Repository
public interface CandidateProfileEducatorRoleRepository extends MongoRepository<CandidateApplicationEducatorRoleEntity, String> {
    @Query("{ '_id': ?0, 'isActive': true }")
    CandidateApplicationEducatorRoleEntity findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<CandidateApplicationEducatorRoleEntity> findAllByIsActiveTrue();

}
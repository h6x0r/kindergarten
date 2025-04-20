package uz.zazu.king.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateProfileEducatorRoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateProfileEducatorRoleRepository extends MongoRepository<CandidateProfileEducatorRoleEntity, String> {
    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<CandidateProfileEducatorRoleEntity> findByIdAndIsActiveTrue(String id);

    @Query(value = "{ 'isActive': true }")
    List<CandidateProfileEducatorRoleEntity> findAllByIsActiveTrue();

}
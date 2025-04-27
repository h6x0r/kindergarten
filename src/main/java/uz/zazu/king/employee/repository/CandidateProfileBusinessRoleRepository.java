package uz.zazu.king.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateProfileBusinessRoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateProfileBusinessRoleRepository extends JpaRepository<CandidateProfileBusinessRoleEntity, String> {

    @Query(value = "SELECT * FROM candidate_business_profiles WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<CandidateProfileBusinessRoleEntity> findByIdAndIsActiveTrue(String id);

    @Query(value = "SELECT * FROM candidate_business_profiles WHERE is_active = true", nativeQuery = true)
    List<CandidateProfileBusinessRoleEntity> findAllByIsActiveTrue();

}
package uz.zazu.king.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.CandidateProfileEducatorRoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateProfileEducatorRoleRepository extends JpaRepository<CandidateProfileEducatorRoleEntity, String> {
    @Query(value = "SELECT * FROM candidate_educator_profiles WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<CandidateProfileEducatorRoleEntity> findByIdAndIsActiveTrue(String id);

    @Query(value = "SELECT * FROM candidate_educator_profiles WHERE is_active = true", nativeQuery = true)
    List<CandidateProfileEducatorRoleEntity> findAllByIsActiveTrue();

}
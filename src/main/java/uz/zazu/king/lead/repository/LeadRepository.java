package uz.zazu.king.lead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.lead.entity.LeadEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, String> {

    @Query(value = "SELECT * FROM leads WHERE is_active = true", nativeQuery = true)
    List<LeadEntity> findAllActive();

    @Query(value = "SELECT * FROM leads WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<LeadEntity> findActiveById(String id);
}
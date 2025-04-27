package uz.zazu.king.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.info.entity.InfoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<InfoEntity, String> {
    @Query(value = "SELECT * FROM info WHERE module = ?1 AND is_active = true", nativeQuery = true)
    List<InfoEntity> findAllActiveByModule(String module);

    @Query(value = "SELECT * FROM info WHERE is_active = true", nativeQuery = true)
    List<InfoEntity> findAllActive();

    @Query(value = "SELECT * FROM info WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<InfoEntity> findActiveById(String id);

}
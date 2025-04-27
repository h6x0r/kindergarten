package uz.zazu.king.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.document.entity.DocumentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, String> {

    @Query(value = "SELECT * FROM documents WHERE is_active = true", nativeQuery = true)
    List<DocumentEntity> findAllActive();

    @Query(value = "SELECT * FROM documents WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<DocumentEntity> findActiveById(String id);

}

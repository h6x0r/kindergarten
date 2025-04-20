package uz.zazu.king.document.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.document.entity.DocumentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {

    @Query("{ 'isActive': true }")
    List<DocumentEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<DocumentEntity> findActiveById(String id);

}

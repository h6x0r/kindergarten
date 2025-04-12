package uz.zazu.king.old.document.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.old.document.entity.DocumentEntity;

import java.util.List;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {

    @Query("{ 'isActive': true }")
    List<DocumentEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    DocumentEntity findActiveById(String id);

}

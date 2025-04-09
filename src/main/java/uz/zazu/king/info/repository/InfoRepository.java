package uz.zazu.king.info.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.info.entity.InfoEntity;

import java.util.List;

@Repository
public interface InfoRepository extends MongoRepository<InfoEntity, String> {
    @Query("{ 'module' : ?0 }")
    List<InfoEntity> findAllByModule(String module);
}
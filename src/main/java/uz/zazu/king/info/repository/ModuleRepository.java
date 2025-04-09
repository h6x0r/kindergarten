package uz.zazu.king.info.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.info.entity.ModuleEntity;

import java.util.List;

@Repository
public interface ModuleRepository extends MongoRepository<ModuleEntity, String> {
    @Query("{ 'moduleName' : ?0 }")
    List<ModuleEntity> findAllByModuleName(String moduleName);
}
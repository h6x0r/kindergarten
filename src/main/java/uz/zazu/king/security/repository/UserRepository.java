package uz.zazu.king.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.security.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    @Query("{ 'isActive': true }")
    List<UserEntity> findAllActive();

    @Query("{ '_id': ?0, 'isActive': true }")
    UserEntity findActiveById(String id);

    @Query("{ 'username': ?0, 'isActive': true }")
    UserEntity findByUserNameAndIsActive(String userName);


}
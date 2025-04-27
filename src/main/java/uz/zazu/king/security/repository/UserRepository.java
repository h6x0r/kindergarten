package uz.zazu.king.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.security.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query(value = "SELECT * FROM users WHERE is_active = true", nativeQuery = true)
    List<UserEntity> findAllActive();

    @Query(value = "SELECT * FROM users WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<UserEntity> findActiveById(String id);

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND is_active = true", nativeQuery = true)
    Optional<UserEntity> findByUserNameAndIsActive(String userName);


}
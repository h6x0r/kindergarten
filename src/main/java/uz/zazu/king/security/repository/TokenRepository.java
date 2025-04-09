//package uz.zazu.king.security.repository;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//import uz.zazu.king.security.entity.TokenEntity;
//
//import java.time.Instant;
//import java.util.Optional;
//
//@Repository
//public interface TokenRepository extends MongoRepository<TokenEntity, String> {
//
//    Optional<TokenEntity> findByToken(String token);
//
//    void deleteAllByUpdatedAtBefore(Instant expirationTime);
//
//    // Для поиска всех токенов конкретного пользователя
//    // List<TokenEntity> findByUserId(String userId);
//}

//package uz.zazu.king.security.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Document("tokens")
//public class TokenEntity {
//
//    @Id
//    private String id;
//    private String userId;
//    private String token;
//    private boolean blacklisted;
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//}

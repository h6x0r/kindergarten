package uz.zazu.king.lead.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "origin_and_date")
    private String originAndDate;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "child_name")
    private String childName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private String comment;
}
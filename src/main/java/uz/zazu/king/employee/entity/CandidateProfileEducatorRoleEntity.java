package uz.zazu.king.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uz.zazu.king.employee.common.enums.CandidateState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_educator_profiles")
public class CandidateProfileEducatorRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "candidate_entry_date")
    private LocalDateTime candidateEntryDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "candidate_status")
    private CandidateState candidateStatus;

    @Column(name = "interview_comments")
    private String interviewComments;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;
}
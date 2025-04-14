package uz.zazu.king.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.zazu.king.employee.common.enums.CandidateState;
import uz.zazu.king.employee.common.enums.EmployeeState;
import uz.zazu.king.employee.common.enums.Position;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "candidate_application_educator")
public class CandidateApplicationEducatorRoleEntity {
    @Id
    private String id;

    private String fullName;
    private LocalDateTime candidateEntryDate;
    private Integer age;
    private String contacts;
    private CandidateState candidateStatus;
    private String interviewComments;
    private Position position;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}
package uz.zazu.king.lead.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.zazu.king.lead.common.enums.LeadState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "leads")
public class LeadEntity {
    @Id
    private String id;

    private long orgId;

    private LocalDateTime leadFirstOccurrence;
    private String leadSource;
    private String parentName;
    private String parentPhone;
    private String childName;
    private int childAge;
    private String childHobbies;
    private String parentNeeds;
    private String desiredBranch;
    private LeadState status;
    private LocalDateTime tourTime;
    private String comment;
    private String paymentInfo;
    private String keyPoints;
    private String purchaseReason;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}
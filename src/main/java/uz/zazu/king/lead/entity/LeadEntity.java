package uz.zazu.king.lead.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uz.zazu.king.lead.common.enums.LeadState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leads")
public class LeadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "org_id")
    private long orgId;

    @Column(name = "lead_first_occurrence")
    private LocalDateTime leadFirstOccurrence;

    @Column(name = "lead_source")
    private String leadSource;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_phone")
    private String parentPhone;

    @Column(name = "child_name")
    private String childName;

    @Column(name = "child_age")
    private int childAge;

    @Column(name = "child_hobbies")
    private String childHobbies;

    @Column(name = "parent_needs")
    private String parentNeeds;

    @Column(name = "desired_branch")
    private String desiredBranch;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LeadState status;

    @Column(name = "tour_time")
    private LocalDateTime tourTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "payment_info")
    private String paymentInfo;

    @Column(name = "key_points")
    private String keyPoints;

    @Column(name = "purchase_reason")
    private String purchaseReason;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;
}
package uz.zazu.king.questionnaire.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uz.zazu.king.common.enums.Branch;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lead_questionnaires")
public class LeadQuestionnaireEntity {

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
    private boolean isActive;

    @Column(name = "org_id")
    private long orgId;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch")
    private Branch branch;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "group_and_teacher")
    private String groupAndTeacher;

    @Column(name = "ltv")
    private long ltv;

    @Column(name = "child_pickup_person")
    private String childPickupPerson;

    @Column(name = "parent_full_name")
    private String parentFullName;

    @Column(name = "child_age_and_dob")
    private String childAgeAndDOB;

    @Column(name = "child_special_needs")
    private String childSpecialNeeds;

    @Column(name = "parent_contact")
    private String parentContact;

    @Column(name = "parent_age")
    private String parentAge;

    @Column(name = "source_info")
    private String sourceInfo;

    @Column(name = "parent_job_and_position")
    private String parentJobAndPosition;

    @Column(name = "priorities")
    private String priorities;

    @Column(name = "leaving_reason")
    private String leavingReason;

    @Column(name = "rating")
    private int rating;

    @Column(name = "improvement_suggestions")
    private String improvementSuggestions;

    @Column(name = "child_satisfaction_level")
    private String childSatisfactionLevel;

    @Column(name = "garden_search_method")
    private String gardenSearchMethod;

    @Column(name = "psychologist_topics")
    private String psychologistTopics;

    @Column(name = "parent_advice")
    private String parentAdvice;

    @Column(name = "recommendation_improvements")
    private String recommendationImprovements;

    @Column(name = "reason_for_choosing")
    private String reasonForChoosing;

    @Column(name = "uzbek_bloggers")
    private String uzbekBloggers;
}

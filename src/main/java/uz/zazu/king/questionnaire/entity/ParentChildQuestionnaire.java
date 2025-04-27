package uz.zazu.king.questionnaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parent_child_questionnaires")
public class ParentChildQuestionnaire {

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

    @Column(name = "child_name")
    private String childName;

    @Column(name = "status_or_reason_for_leaving")
    private String statusOrReasonForLeaving;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "child_birth_date")
    private LocalDate childBirthDate;

    @Column(name = "parent_contact")
    private String parentContact;

    @Column(name = "parent_age")
    private Integer parentAge;

    @Column(name = "group_and_teacher")
    private String groupAndTeacher;

    @Column(name = "ltv")
    private String ltv;

    @Column(name = "has_documents")
    private Boolean hasDocuments;

    @Column(name = "who_picks_up_child")
    private String whoPicksUpChild;

    @Column(name = "how_did_you_find_out")
    private String howDidYouFindOut;

    @Column(name = "work_details")
    private String workDetails;

    @Column(name = "priorities")
    private String priorities;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "what_to_improve")
    private String whatToImprove;

    @Column(name = "child_satisfaction_level")
    private String childSatisfactionLevel;

    @Column(name = "leaving_reason")
    private String leavingReason;

    @Column(name = "how_did_you_search")
    private String howDidYouSearch;

    @Column(name = "psychologist_suggestions")
    private String psychologistSuggestions;

    @Column(name = "parent_needs")
    private String parentNeeds;

    @Column(name = "promotion_advice")
    private String promotionAdvice;

    @Column(name = "recommendation_condition")
    private String recommendationCondition;

    @Column(name = "uzb_bloggers")
    private String uzbBloggers;

    @Column(name = "instagram_user")
    private String instagramUser;

    @Column(name = "why_our_kindergarten")
    private String whyOurKindergarten;

}
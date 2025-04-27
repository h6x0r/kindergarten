package uz.zazu.king.questionnaire.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "marketing_questionnaires")
public class MarketingQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "filial")
    private String filial;

    @Column(name = "status_or_reason_for_leaving")
    private String statusOrReasonForLeaving;

    @Column(name = "child_age_and_birth_date")
    private String childAgeAndBirthDate;

    @Column(name = "parents_age")
    private String parentsAge;

    @Column(name = "ad_source_description")
    private String adSourceDescription;

    @Column(name = "parents_job")
    private String parentsJob;

    @Column(name = "priorities")
    private String priorities;

    @Column(name = "reason_to_leave")
    private String reasonToLeave;

    @Column(name = "search_method")
    private String searchMethod;

    @Column(name = "psychologist_topics")
    private String psychologistTopics;

    @Column(name = "parents_needs")
    private String parentsNeeds;

    @Column(name = "promo_advice")
    private String promoAdvice;

    @Column(name = "recommendation_condition")
    private String recommendationCondition;

    @Column(name = "uzb_bloggers")
    private String uzbBloggers;

    @Column(name = "why_our_kindergarten")
    private String whyOurKindergarten;
}
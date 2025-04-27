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
@Table(name = "candidate_nanny_profiles")
public class CandidateProfileNannyRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "candidate_entry_date")
    private LocalDateTime candidateEntryDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "candidate_status")
    @Enumerated(EnumType.STRING)
    private CandidateState candidateStatus;

    @Column(name = "punctuality")
    private Boolean punctuality;

    @Column(name = "convenient_branch")
    private String convenientBranch;

    @Column(name = "how_did_they_hear_about_us")
    private String howDidTheyHearAboutUs;

    @Column(name = "education")
    private String education;

    @Column(name = "previous_job_details")
    private String previousJobDetails;

    @Column(name = "has_children")
    private Boolean hasChildren;

    @Column(name = "career_development_plans")
    private String careerDevelopmentPlans;

    @Column(name = "salary_expectations")
    private String salaryExpectations;

    @Column(name = "reason_for_working_in_this_field")
    private String reasonForWorkingInThisField;

    @Column(name = "important_rules_for_working_with_children")
    private String importantRulesForWorkingWithChildren;

    @Column(name = "handling_child_refusal_to_eat")
    private String handlingChildRefusalToEat;

    @Column(name = "last_learning_experience")
    private String lastLearningExperience;

    @Column(name = "personal_interests")
    private String personalInterests;

    @Column(name = "dislikes_in_people")
    private String dislikesInPeople;

    @Column(name = "ideal_job_description")
    private String idealJobDescription;

    @Column(name = "things_that_bring_joy")
    private String thingsThatBringJoy;

    @Column(name = "preferred_error_feedback_method")
    private String preferredErrorFeedbackMethod;

    @Column(name = "preferred_motivation_method")
    private String preferredMotivationMethod;

    @Column(name = "likes_in_people")
    private String likesInPeople;

    @Column(name = "suitability_rating")
    private Integer suitabilityRating;

    @Column(name = "interview_comments")
    private String interviewComments;

    @Column(name = "final_decision_after_interview")
    private String finalDecisionAfterInterview;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;
}
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
@Document(collection = "candidate_application_nanny")
public class CandidateApplicationNannyRoleEntity {
    @Id
    private String id;

    private String fullName;
    private LocalDateTime candidateEntryDate;
    private Integer age;
    private String contacts;
    private CandidateState candidateStatus;
    private Boolean punctuality;
    private String convenientBranch;
    private String howDidTheyHearAboutUs;
    private String education;
    private String previousJobDetails;
    private Boolean hasChildren;
    private String careerDevelopmentPlans;
    private String salaryExpectations;
    private String reasonForWorkingInThisField;
    private String importantRulesForWorkingWithChildren;
    private String handlingChildRefusalToEat;
    private String lastLearningExperience;
    private String personalInterests;
    private String dislikesInPeople;
    private String idealJobDescription;
    private String thingsThatBringJoy;
    private String preferredErrorFeedbackMethod;
    private String preferredMotivationMethod;
    private String likesInPeople;
    private Integer suitabilityRating;
    private String interviewComments;
    private String finalDecisionAfterInterview;
    private Position position;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}
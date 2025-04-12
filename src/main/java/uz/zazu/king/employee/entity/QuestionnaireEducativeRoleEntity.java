package uz.zazu.king.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.zazu.king.employee.common.enums.EmployeeState;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questionnaires_business_role")
public class QuestionnaireEducativeRoleEntity {
    @Id
    private String id;

    private String fullName;
    private LocalDateTime candidateEntryDate;
    private Integer age;
    private String contacts;
    private EmployeeState candidateStatus;
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
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}
package uz.zazu.king.employee.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.zazu.king.employee.common.enums.EmployeeState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("educative")
public class QuestionnaireEducativeRoleDto implements QuestionnaireDto {
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
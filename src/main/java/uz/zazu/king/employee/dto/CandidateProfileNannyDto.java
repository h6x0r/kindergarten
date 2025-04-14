package uz.zazu.king.employee.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("nanny")
public class CandidateProfileNannyDto extends CandidateProfileDto {
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
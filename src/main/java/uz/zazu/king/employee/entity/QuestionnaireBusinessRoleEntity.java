package uz.zazu.king.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questionnaires_business_role")
public class QuestionnaireBusinessRoleEntity {
    @Id
    private String id;

    private String fullName;
    private LocalDateTime candidateEntryDate;
    private Integer age;
    private String contacts;
    private String candidateStatus;
    private String resumeLink;
    private String previousCompanyName;
    private String previousPositionName;
    private String responsibilities;
    private String dutiesPerformed;
    private String handlingChallengesOrConflicts;
    private String strengthsAndWeaknesses;
    private String workingRelationsWithColleagues;
    private String reasonForLeavingCompany;
    private String reconsiderationForRehire;
    private String multitaskingAndProjectManagement;
    private Integer responsibilityScore;
    private String examplesOfLeadership;
    private String reliabilityAndTaskCompletion;
    private String initiativeAndIdeas;
    private String pastSalary;
    private String motivationFactors;
    private String handlingCriticism;
    private String willingnessToLearnAndDevelop;
    private Boolean punctuality;
    private String convenientBranch;
    private String howDidTheyHearAboutUs;
    private String education;
    private String previousJobDetails;
    private String handlingTimeConstraints;
    private String recentLearningExperience;
    private String personalInterests;
    private String perceptionOfPeopleDesires;
    private String reasonsForJobChange;
    private String easeOfCollaboration;
    private String salaryExpectations;
    private String idealJobConcept;
    private String thingsThatBringJoy;
    private String bestErrorHandlingMethod;
    private String bestMotivationalMethod;
    private String dislikesInPeople;
    private Integer suitabilityRating;
    private String understandingOfBusinessAssistantRole;
    private String desiredSkillsForAssistant;
    private String interestInPositionAndCompany;
    private String careerPathVision;
    private String motivatingPastTasks;
    private String handlingRoutineTasks;
    private String idealWorkEnvironment;
    private String handlingConflicts;
    private String criticismAcceptanceExperience;
    private String managingLimitedResources;
    private String managedProjectsDetails;
    private String usedToolsAndMethodsForManagement;
    private String preferredToolsForTaskOrganization;
    private String taskPrioritizationApproach;
    private String idealRelationshipWithSupervisor;
    private String workPlanningTools;
    private String highWorkloadOrganization;
    private String trackingWorkEffectiveness;
    private String handlingOverwhelmingTasks;
    private String adaptingToNewConditions;
    private String desiredSkillsToDevelop;
    private String referenceCompanyContacts;
    private String interviewDecision;
    private String interviewStatusMetric;
    private String offlineInterviewMetrics;
    private String testTaskEfficiencyMetric;
    private String previousCompanyMetrics;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}
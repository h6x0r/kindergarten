package uz.zazu.king.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uz.zazu.king.employee.common.enums.Position;
import uz.zazu.king.info.enums.Module;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_business_profiles")
public class CandidateProfileBusinessRoleEntity {

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
    private String candidateStatus;

    @Column(name = "resume_link")
    private String resumeLink;

    @Column(name = "previous_company_name")
    private String previousCompanyName;

    @Column(name = "previous_position_name")
    private String previousPositionName;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "duties_performed")
    private String dutiesPerformed;

    @Column(name = "handling_challenges_or_conflicts")
    private String handlingChallengesOrConflicts;

    @Column(name = "strengths_and_weaknesses")
    private String strengthsAndWeaknesses;

    @Column(name = "working_relations_with_colleagues")
    private String workingRelationsWithColleagues;

    @Column(name = "reason_for_leaving_company")
    private String reasonForLeavingCompany;

    @Column(name = "reconsideration_for_rehire")
    private String reconsiderationForRehire;

    @Column(name = "multitasking_and_project_management")
    private String multitaskingAndProjectManagement;

    @Column(name = "responsibility_score")
    private Integer responsibilityScore;

    @Column(name = "examples_of_leadership")
    private String examplesOfLeadership;

    @Column(name = "reliability_and_task_completion")
    private String reliabilityAndTaskCompletion;

    @Column(name = "initiative_and_ideas")
    private String initiativeAndIdeas;

    @Column(name = "past_salary")
    private String pastSalary;

    @Column(name = "motivation_factors")
    private String motivationFactors;

    @Column(name = "handling_criticism")
    private String handlingCriticism;

    @Column(name = "willingness_to_learn_and_develop")
    private String willingnessToLearnAndDevelop;

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

    @Column(name = "handling_time_constraints")
    private String handlingTimeConstraints;

    @Column(name = "recent_learning_experience")
    private String recentLearningExperience;

    @Column(name = "personal_interests")
    private String personalInterests;

    @Column(name = "perception_of_people_desires")
    private String perceptionOfPeopleDesires;

    @Column(name = "reasons_for_job_change")
    private String reasonsForJobChange;

    @Column(name = "ease_of_collaboration")
    private String easeOfCollaboration;

    @Column(name = "salary_expectations")
    private String salaryExpectations;

    @Column(name = "ideal_job_concept")
    private String idealJobConcept;

    @Column(name = "things_that_bring_joy")
    private String thingsThatBringJoy;

    @Column(name = "best_error_handling_method")
    private String bestErrorHandlingMethod;

    @Column(name = "best_motivational_method")
    private String bestMotivationalMethod;

    @Column(name = "dislikes_in_people")
    private String dislikesInPeople;

    @Column(name = "suitability_rating")
    private Integer suitabilityRating;

    @Column(name = "understanding_of_business_assistant_role")
    private String understandingOfBusinessAssistantRole;

    @Column(name = "desired_skills_for_assistant")
    private String desiredSkillsForAssistant;

    @Column(name = "interest_in_position_and_company")
    private String interestInPositionAndCompany;

    @Column(name = "career_path_vision")
    private String careerPathVision;

    @Column(name = "motivating_past_tasks")
    private String motivatingPastTasks;

    @Column(name = "handling_routine_tasks")
    private String handlingRoutineTasks;

    @Column(name = "ideal_work_environment")
    private String idealWorkEnvironment;

    @Column(name = "handling_conflicts")
    private String handlingConflicts;

    @Column(name = "criticism_acceptance_experience")
    private String criticismAcceptanceExperience;

    @Column(name = "managing_limited_resources")
    private String managingLimitedResources;

    @Column(name = "managed_projects_details")
    private String managedProjectsDetails;

    @Column(name = "used_tools_and_methods_for_management")
    private String usedToolsAndMethodsForManagement;

    @Column(name = "preferred_tools_for_task_organization")
    private String preferredToolsForTaskOrganization;

    @Column(name = "task_prioritization_approach")
    private String taskPrioritizationApproach;

    @Column(name = "ideal_relationship_with_supervisor")
    private String idealRelationshipWithSupervisor;

    @Column(name = "work_planning_tools")
    private String workPlanningTools;

    @Column(name = "high_workload_organization")
    private String highWorkloadOrganization;

    @Column(name = "tracking_work_effectiveness")
    private String trackingWorkEffectiveness;

    @Column(name = "handling_overwhelming_tasks")
    private String handlingOverwhelmingTasks;

    @Column(name = "adapting_to_new_conditions")
    private String adaptingToNewConditions;

    @Column(name = "desired_skills_to_develop")
    private String desiredSkillsToDevelop;

    @Column(name = "reference_company_contacts")
    private String referenceCompanyContacts;

    @Column(name = "interview_decision")
    private String interviewDecision;

    @Column(name = "interview_status_metric")
    private String interviewStatusMetric;

    @Column(name = "offline_interview_metrics")
    private String offlineInterviewMetrics;

    @Column(name = "test_task_efficiency_metric")
    private String testTaskEfficiencyMetric;

    @Column(name = "previous_company_metrics")
    private String previousCompanyMetrics;

    @Column(name = "position")
    private Position position;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "module")
    private Module module;
}
package uz.zazu.king.lead.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LeadQuestionnaireDto {
    private String id;
    private long orgId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String groupAndTeacher;
    private long ltv;
    private String childPickupPerson;
    private String parentFullName;
    private String childAgeAndDOB;
    private String childSpecialNeeds;
    private String parentContact;
    private String parentAge;
    private String sourceInfo;
    private String parentJobAndPosition;
    private String priorities;
    private String leavingReason;
    private int rating;
    private String improvementSuggestions;
    private String childSatisfactionLevel;
    private String gardenSearchMethod;
    private String psychologistTopics;
    private String parentAdvice;
    private String recommendationImprovements;
    private String reasonForChoosing;
    private String uzbekBloggers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
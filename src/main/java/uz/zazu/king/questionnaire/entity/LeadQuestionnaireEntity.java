package uz.zazu.king.questionnaire.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.zazu.king.common.enums.Branch;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lead_questionnaires")
public class LeadQuestionnaireEntity {
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;

    private long orgId;
    private Branch branch;
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
}

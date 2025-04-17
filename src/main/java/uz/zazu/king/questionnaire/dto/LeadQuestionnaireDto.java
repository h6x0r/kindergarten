package uz.zazu.king.questionnaire.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadQuestionnaireDto {
    private String id;
    private long orgId;
    @NotBlank(message = "Имя не должно быть пустым")
    private String firstName;
    private String lastName;
    private String patronymic;
    @NotBlank(message = "Группа и преподаватель не должны быть пустыми")
    private String groupAndTeacher;
    private long ltv;
    @NotBlank(message = "ФИО ответственного за забор ребенка не должно быть пустым")
    private String childPickupPerson;
    @NotBlank(message = "ФИО родителя не должно быть пустым")
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
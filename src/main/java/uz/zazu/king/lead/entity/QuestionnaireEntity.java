package uz.zazu.king.lead.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.zazu.king.lead.common.Branch;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questionnaire")
public class QuestionnaireEntity {
    @Id
    private String id;

    private long orgId;
    private Branch branch;
    private String firstName;   // имя ребенка
    private String lastName;   // фамилия ребенка
    private String patronymic;   // отчество ребенка
    private String groupAndTeacher; // группа и педагог
    private long ltv;               // общее время пребывания в саду
    private String childPickupPerson; // кто забирает ребенка
    private String parentFullName;  // ФИО родителей
    private String childAgeAndDOB;  // возраст и дата рождения ребенка
    private String childSpecialNeeds; // особенности ребенка
    private String parentContact;   // контакты родителя
    private String parentAge;       // возраст родителей
    private String sourceInfo;      // откуда узнали
    private String parentJobAndPosition; // место и должность работы родителей
    private String priorities;          // приоритеты выбора
    private String leavingReason;       // причина покидания
    private int rating;             // оценка от 1 до 10
    private String improvementSuggestions;  // как улучшить оценку до 10
    private String childSatisfactionLevel;  // уровень удовлетворенности ребенка
    private String gardenSearchMethod;      // как нашли сад
    private String psychologistTopics;      // темы для тренингов
    private String parentAdvice;            // советы для новых родителей
    private String recommendationImprovements; // что сделать для рекомендаций
    private String reasonForChoosing;       // почему выбрали
    private String uzbekBloggers;           // узбекские блогеры
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}

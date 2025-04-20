package uz.zazu.king.questionnaire.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "new_questionnaire")
public class MarketingQuestionnaire {
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;

    private String filial;
    private String statusOrReasonForLeaving;
    private String childAgeAndBirthDate;
    private String parentsAge;
    private String adSourceDescription;
    private String parentsJob;
    private String priorities;
    private String reasonToLeave;
    private String searchMethod;
    private String psychologistTopics;
    private String parentsNeeds;
    private String promoAdvice;
    private String recommendationCondition;
    private String uzbBloggers;
    private String whyOurKindergarten;
}
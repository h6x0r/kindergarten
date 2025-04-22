package uz.zazu.king.questionnaire.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "parent_child_questionnaires")
public class ParentChildQuestionnaire {
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean isActive;

    private String childName;
    private String statusOrReasonForLeaving;
    private String parentName;
    private LocalDate childBirthDate;
    private String parentContact;
    private Integer parentAge;
    private String groupAndTeacher;
    private String ltv;
    private Boolean hasDocuments;
    private String whoPicksUpChild;
    private String howDidYouFindOut;
    private String workDetails;
    private String priorities;
    private Integer rating;
    private String whatToImprove;
    private String childSatisfactionLevel;
    private String leavingReason;
    private String howDidYouSearch;
    private String psychologistSuggestions;
    private String parentNeeds;
    private String promotionAdvice;
    private String recommendationCondition;
    private String uzbBloggers;
    private String instagramUser;
    private String whyOurKindergarten;

}
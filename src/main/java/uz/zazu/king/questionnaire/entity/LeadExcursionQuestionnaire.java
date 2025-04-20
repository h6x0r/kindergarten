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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lead_excursion_questionnaires")
public class LeadExcursionQuestionnaire {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    private boolean isActive;

    // Поля, необходимые для вашего приложения
    private LocalDate date;
    private String filial;
    private String parentAndChild;
    private String finalDecision;
    private String searchMethod;
    private Boolean hasKindergartenExperience;
    private String departureReason;
    private String selectionCriteria;
    private String staffConsiderations;
    private String childDescription;
    private String institutionExpectations;
    private String childDevelopmentResult;
    private String priorityCriteria;
    private String possibleLeavingReasons;
    private String privateSectorChoiceReason;
    private String infoSource;
}
package uz.zazu.king.questionnaire.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lead_excursion_questionnaires")
public class LeadExcursionQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "filial")
    private String filial;

    @Column(name = "parent_and_child")
    private String parentAndChild;

    @Column(name = "final_decision")
    private String finalDecision;

    @Column(name = "search_method")
    private String searchMethod;

    @Column(name = "has_kindergarten_experience")
    private Boolean hasKindergartenExperience;

    @Column(name = "departure_reason")
    private String departureReason;

    @Column(name = "selection_criteria")
    private String selectionCriteria;

    @Column(name = "staff_considerations")
    private String staffConsiderations;

    @Column(name = "child_description")
    private String childDescription;

    @Column(name = "institution_expectations")
    private String institutionExpectations;

    @Column(name = "child_development_result")
    private String childDevelopmentResult;

    @Column(name = "priority_criteria")
    private String priorityCriteria;

    @Column(name = "possible_leaving_reasons")
    private String possibleLeavingReasons;

    @Column(name = "private_sector_choice_reason")
    private String privateSectorChoiceReason;

    @Column(name = "info_source")
    private String infoSource;
}
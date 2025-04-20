package uz.zazu.king.questionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.zazu.king.questionnaire.common.enums.Branch;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadExcursionQuestionnaireDto {
    // 1) Дата
    private LocalDate date;

    // 2) Филиал
    private Branch branch;

    // 3) Родитель + ребёнок
    private String parentAndChild;

    // 4) Что решили по окончанию
    private String finalDecision;

    // 5) Как искали сад?
    private String searchMethod;

    // 6) Есть ли у вас опыт в посещении дет сада?
    //    Если да: если не секрет, можно ли узнать причину вашего ухода?
    private Boolean hasKindergartenExperience;
    private String departureReason;

    // 7) По каким критериям вы выбираете сад?
    private String selectionCriteria;

    // 8) Что нужно учитывать нашему персоналу для комфортного пребывания вашего ребёнка в саду?
    private String staffConsiderations;

    // 9) Расскажите про вашего ребёнка: интересы, любимые занятия и игры
    private String childDescription;

    // 10) Какие у вас ожидания от учреждения?
    private String institutionExpectations;

    // 11) Что для вас является результатом развития ребёнка?
    private String childDevelopmentResult;

    // 12) Что является приоритетом для вас при выборе сада?
    private String priorityCriteria;

    // 13) По каким причинам вы можете уйти из сада?
    private String possibleLeavingReasons;

    // 14) Почему вы выбрали для своего ребёнка именно частный сектор, а не гос сад?
    private String privateSectorChoiceReason;

    // 15) Откуда вы о нас узнали?
    private String infoSource;
}

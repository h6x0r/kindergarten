package uz.zazu.king.questionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentChildQuestionnaireDto {
    private String childName;                // Имя ребёнка
    private String statusOrReasonForLeaving; // Статус / причина ухода
    private String parentName;               // Имя родителя
    private LocalDate childBirthDate;        // Дата рождения ребёнка
    private String parentContact;            // Контакты родителя
    private Integer parentAge;               // Возраст родителей
    private String groupAndTeacher;          // Группа + имя педагога
    private String ltv;                      // LTV
    private Boolean hasDocuments;            // Наличие документов
    private String whoPicksUpChild;          // Кто забирает ребёнка
    private String howDidYouFindOut;         // Откуда узнали о саде (реклама и пр.)
    private String workDetails;              // Кем работают? Максимально подробно
    private String priorities;               // Приоритеты при выборе сада
    private Integer rating;                  // Оценка сада (1–10)
    private String whatToImprove;            // Что сделать, чтобы был балл 10?
    private String childSatisfactionLevel;   // Уровень удовлетворённости ребёнка
    private String leavingReason;            // Причина, по которой могли бы покинуть сад
    private String howDidYouSearch;          // Как искали сад?
    private String psychologistSuggestions;  // На какую тематику тренинги/обзоры?
    private String parentNeeds;              // Что сейчас нужно всем родителям?
    private String promotionAdvice;          // Советы по продвижению
    private String recommendationCondition;  // Что сделать, чтобы рекомендовали сад?
    private String uzbBloggers;              // Каких узб блогеров смотрите?
    private String instagramUser;            // Юзер Instagram (если не секрет)
    private String whyOurKindergarten;       // Почему выбрали именно наш сад?
}
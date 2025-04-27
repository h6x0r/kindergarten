package uz.zazu.king.questionnaire.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketingQuestionnaireDto {
    private String id;
    private String filial;                       // Филиал
    private String statusOrReasonForLeaving;     // Статус / Причина ухода
    private String childAgeAndBirthDate;         // Возраст ребёнка & дата рождения
    private String parentsAge;                   // Возраст родителей
    private String adSourceDescription;          // Откуда узнали о нашем саде? + описание рекламы
    private String parentsJob;                   // Кем работают? Максимально подробно
    private String priorities;                   // Приоритеты при выборе сада
    private String reasonToLeave;                // По какой причине вы могли бы покинуть сад?
    private String searchMethod;                 // Как вы искали сад?
    private String psychologistTopics;           // Темы тренировок и обзоров от психолога
    private String parentsNeeds;                 // Что сейчас нужно всем родителям?
    private String promoAdvice;                  // Советы по продвижению
    private String recommendationCondition;      // Что нужно сделать, чтобы рекомендовали нас?
    private String uzbBloggers;                 // Каких узб блогеров вы смотрите?
    private String whyOurKindergarten;           // Почему выбрали именно наш сад?
}
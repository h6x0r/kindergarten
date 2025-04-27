package uz.zazu.king.questionnaire.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeQuestionnaireDto {
    private String id;
    private String branch;                      // Филиал
    private String fullName;                    // ФИО
    private String phoneNumber;                 // Номер
    private String position;                    // Должность
    private String status;                      // Статус
    private String maritalStatus;               // Семейное положение
    private Double salary;                      // ЗП
    private Double ltv;                         // LTV
    private Integer satisfactionLevel;          // Уровень удовлетворённости
    private String motivationPreferences;       // Какие способы мотивации/поощрения вы предпочитаете?
    private String ideasToEaseWork;             // Как бы мы могли облегчить вашу работу?
    private String thingsYouTolerate;           // Что вам приходится терпеть на работе?
    private String orgImprovements;             // Какие изменения в организации вы бы хотели видеть?
    private String mostLikedAboutWork;          // Что вам больше всего нравится в вашей работе?
    private String dreamJob;                    // Какая ваша работа мечты? Кем вы хотели всю жизнь работать?
    private String nearTwoYearsPlans;           // Какие планы на ближайшие два года?
    private String inFiveYears;                 // Кем вы видите себя через пять лет?
    private String reasonWorkingWithUs;         // Почему вы выбрали работать с нами?
    private Integer rating;                     // Рейтинг сотрудника
    private String comments;                    // Комментарии
    private String managerComments;             // Комментарии менеджера
}
package uz.zazu.king.lead.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto {
    private String originAndDate;    // откуда/когда
    private String parentName;       // имя родителя
    private String phoneNumber;      // номер телефона
    private String childName;        // имя ребенка
    private Integer age;             // возраст
    private String status;           // статус
    private String comment;          // комментарий
}
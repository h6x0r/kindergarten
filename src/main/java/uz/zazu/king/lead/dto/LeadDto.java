package uz.zazu.king.lead.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadDto {
    private String id;
    private String name;
    private String contact;
    private String leadSource;     // источник лида
    private String parentName;     // имя родителя
    private String parentPhone;    // номер телефона
    private String childName;      // имя ребенка
    private int childAge;
    private String childHobbies;   // увлечения ребенка
    private String parentNeeds;    // потребность родителя
    private String desiredBranch;  // желаемый филиал
    private String status;         // статус
    private LocalDateTime tourTime;
    private String comment;        // комментарий
    private String paymentInfo;    // оплата
    private String keyPoints;      // ключевые моменты
    private String purchaseReason; // причина покупки/не покупки
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package uz.zazu.king.lead.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.zazu.king.lead.common.enums.LeadState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadDto {
    private String id;
    private LocalDateTime leadFirstOccurrence;
    private String leadSource;     // источник лида
    @NotBlank(message = "Имя родителя не должно быть пустым")
    private String parentName;     // имя родителя
    @NotBlank(message = "Номер телефона не должен быть пустым")
    private String parentPhone;    // номер телефона
    @NotBlank(message = "Имя ребенка не должно быть пустым")
    private String childName;      // имя ребенка
    private int childAge;
    private String childHobbies;   // увлечения ребенка
    private String parentNeeds;    // потребность родителя
    private String desiredBranch;  // желаемый филиал
    private LeadState status;         // статус
    private LocalDateTime tourTime;
    private String comment;        // комментарий
    private String paymentInfo;    // оплата
    private String keyPoints;      // ключевые моменты
    private String purchaseReason; // причина покупки/не покупки
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
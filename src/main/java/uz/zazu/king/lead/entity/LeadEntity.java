package uz.zazu.king.lead.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.zazu.king.lead.common.LeadState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "leads")
public class LeadEntity {
    @Id
    private String id;

    private long orgId;

    private LocalDateTime leadFirstOccurrence;
    private String leadSource;     // источник лида
    private String parentName;     // имя родителя
    private String parentPhone;    // номер телефона
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
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;
}
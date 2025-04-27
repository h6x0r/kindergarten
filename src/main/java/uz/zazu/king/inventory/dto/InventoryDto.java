package uz.zazu.king.inventory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private String id;
    private long orgId;
    private long productId;
    private long price;
    private String itemName;
    private long count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package uz.zazu.king.inventory.dto;

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
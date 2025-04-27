package uz.zazu.king.document.dto;

import lombok.*;
import uz.zazu.king.common.enums.Branch;
import uz.zazu.king.document.commons.enums.DocType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private String id;
    private long orgId;
    private Branch branch;
    private DocType docType;
    private String title;
    private String contentPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
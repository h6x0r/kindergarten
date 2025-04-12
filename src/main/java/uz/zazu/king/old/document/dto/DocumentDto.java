package uz.zazu.king.old.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.zazu.king.old.document.commons.enums.DocType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private String id;
    private long orgId;
    private DocType docType;
    private String title;
    private String contentPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
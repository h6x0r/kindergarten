package uz.zazu.king.document.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uz.zazu.king.common.enums.Branch;
import uz.zazu.king.document.commons.enums.DocType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "org_id")
    private long orgId;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch")
    private Branch branch;

    @Enumerated(EnumType.STRING)
    @Column(name = "doc_type")
    private DocType docType;

    @Column(name = "title")
    private String title;

    @Column(name = "google_doc_url")
    private String googleDocUrl;
}

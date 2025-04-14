package uz.zazu.king.employee.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.zazu.king.employee.common.enums.CandidateState;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = CandidateProfileBusinessDto.class, name = "business"),
                @JsonSubTypes.Type(value = CandidateProfileEducatorDto.class, name = "educator"),
                @JsonSubTypes.Type(value = CandidateProfileNannyDto.class, name = "nanny")
        }
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfileDto {
    private String id;
    private String fullName;
    private LocalDateTime candidateEntryDate;
    private Integer age;
    private String contacts;
    private CandidateState candidateStatus;
    private String interviewComments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

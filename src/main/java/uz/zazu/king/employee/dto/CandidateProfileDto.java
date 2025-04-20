package uz.zazu.king.employee.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.zazu.king.employee.common.enums.CandidateState;
import uz.zazu.king.info.enums.Module;

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
    @NotBlank(message = "Поле 'Полное имя' не должно быть пустым")
    private String fullName;
    @NotBlank(message = "Поле 'Дата входа кандидата' не должно быть пустым")
    private LocalDateTime candidateEntryDate;
    @NotBlank(message = "Поле 'Возраст' не должно быть пустым")
    private Integer age;
    @NotBlank(message = "Поле 'Контакты' не должно быть пустым")
    private String contacts;
    @NotBlank(message = "Поле 'Статус кандидата' не должно быть пустым")
    private CandidateState candidateStatus;
    private String interviewComments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Module module;
}

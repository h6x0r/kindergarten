package uz.zazu.king.employee.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = QuestionnaireBusinessRoleDto.class, name = "business"),
                @JsonSubTypes.Type(value = QuestionnaireEducativeRoleDto.class, name = "educative")}
)
public interface QuestionnaireDto {
}

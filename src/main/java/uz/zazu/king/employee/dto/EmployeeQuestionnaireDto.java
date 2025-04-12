package uz.zazu.king.employee.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = EmployeeQuestionnaireBusinessRoleDto.class, name = "business"),
                @JsonSubTypes.Type(value = EmployeeQuestionnaireEducativeRoleDto.class, name = "educative")}
)
public interface EmployeeQuestionnaireDto {
}

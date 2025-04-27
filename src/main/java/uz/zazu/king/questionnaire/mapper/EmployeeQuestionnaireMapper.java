package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.*;
import uz.zazu.king.questionnaire.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.EmployeeQuestionnaire;

@Mapper(componentModel = "spring")
public interface EmployeeQuestionnaireMapper {

    @Mapping(target = "isActive", constant = "true")
    EmployeeQuestionnaire toEntity(EmployeeQuestionnaireDto dto);

    EmployeeQuestionnaireDto toDto(EmployeeQuestionnaire entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(EmployeeQuestionnaireDto dto, @MappingTarget EmployeeQuestionnaire entity);
}
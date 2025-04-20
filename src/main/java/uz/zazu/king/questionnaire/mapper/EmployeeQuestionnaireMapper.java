package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.zazu.king.questionnaire.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.EmployeeQuestionnaire;

@Mapper(componentModel = "spring")
public interface EmployeeQuestionnaireMapper {

    @Mapping(target = "active", constant = "true")
    EmployeeQuestionnaire toEntity(EmployeeQuestionnaireDto dto);

    EmployeeQuestionnaireDto toDto(EmployeeQuestionnaire entity);

    void updateFromDto(EmployeeQuestionnaireDto dto,
                       @MappingTarget EmployeeQuestionnaire entity);
}
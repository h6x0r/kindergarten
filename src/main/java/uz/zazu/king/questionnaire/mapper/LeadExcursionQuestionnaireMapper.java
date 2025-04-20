package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.zazu.king.questionnaire.dto.LeadExcursionQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.LeadExcursionQuestionnaire;

@Mapper(componentModel = "spring")
public interface LeadExcursionQuestionnaireMapper {

    LeadExcursionQuestionnaireDto toDto(LeadExcursionQuestionnaire entity);

    @Mapping(target = "isActive", constant = "true")
    LeadExcursionQuestionnaire toEntity(LeadExcursionQuestionnaireDto dto);

    void updateEntityFromDto(LeadExcursionQuestionnaireDto dto, @MappingTarget LeadExcursionQuestionnaire entity);
}
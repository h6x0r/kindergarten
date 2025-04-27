package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.*;
import uz.zazu.king.questionnaire.dto.LeadExcursionQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.LeadExcursionQuestionnaire;

@Mapper(componentModel = "spring")
public interface LeadExcursionQuestionnaireMapper {

    LeadExcursionQuestionnaireDto toDto(LeadExcursionQuestionnaire entity);

    @Mapping(target = "isActive", constant = "true")
    LeadExcursionQuestionnaire toEntity(LeadExcursionQuestionnaireDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LeadExcursionQuestionnaireDto dto, @MappingTarget LeadExcursionQuestionnaire entity);
}
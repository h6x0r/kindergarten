package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.*;
import uz.zazu.king.questionnaire.dto.MarketingQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.MarketingQuestionnaire;

@Mapper(componentModel = "spring")
public interface MarketingQuestionnaireMapper {

    @Mapping(target = "isActive", constant = "true")
    MarketingQuestionnaire toEntity(MarketingQuestionnaireDto dto);

    MarketingQuestionnaireDto toDto(MarketingQuestionnaire entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget MarketingQuestionnaire entity, MarketingQuestionnaireDto dto);
}
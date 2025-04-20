package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.zazu.king.questionnaire.dto.MarketingQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.MarketingQuestionnaire;

@Mapper(componentModel = "spring")
public interface MarketingQuestionnaireMapper {

    @Mapping(target = "active", constant = "true")
    MarketingQuestionnaire toEntity(MarketingQuestionnaireDto dto);

    MarketingQuestionnaireDto toDto(MarketingQuestionnaire entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget MarketingQuestionnaire entity, MarketingQuestionnaireDto dto);
}
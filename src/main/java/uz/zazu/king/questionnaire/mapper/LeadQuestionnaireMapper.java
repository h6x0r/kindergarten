package uz.zazu.king.questionnaire.mapper;

import org.mapstruct.*;
import uz.zazu.king.questionnaire.dto.LeadQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.LeadQuestionnaireEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LeadQuestionnaireMapper {

    @Mapping(target = "isActive", constant = "true")
    LeadQuestionnaireEntity toEntity(LeadQuestionnaireDto dto);

    LeadQuestionnaireDto toDto(LeadQuestionnaireEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LeadQuestionnaireDto dto, @MappingTarget LeadQuestionnaireEntity entity);
}
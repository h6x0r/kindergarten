package uz.zazu.king.questionnaire.mapper;


import org.mapstruct.*;
import uz.zazu.king.questionnaire.dto.ParentChildQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.ParentChildQuestionnaire;

@Mapper(componentModel = "spring")
public interface ParentChildQuestionnaireMapper {

    @Mapping(target = "isActive", constant = "true")
    ParentChildQuestionnaire toEntity(ParentChildQuestionnaireDto dto);

    ParentChildQuestionnaireDto toDto(ParentChildQuestionnaire entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ParentChildQuestionnaireDto dto, @MappingTarget ParentChildQuestionnaire entity);
}
package uz.zazu.king.questionnaire.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
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
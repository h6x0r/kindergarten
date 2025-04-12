package uz.zazu.king.lead.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.lead.dto.LeadQuestionnaireDto;
import uz.zazu.king.lead.entity.QuestionnaireEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);

    @Mapping(target = "isActive", constant = "true")
    QuestionnaireEntity toEntity(LeadQuestionnaireDto dto);

    LeadQuestionnaireDto toDto(QuestionnaireEntity entity);

    void updateEntityFromDto(LeadQuestionnaireDto dto, @MappingTarget QuestionnaireEntity entity);
}
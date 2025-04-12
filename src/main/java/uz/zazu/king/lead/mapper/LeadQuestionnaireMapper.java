package uz.zazu.king.lead.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.zazu.king.lead.dto.LeadQuestionnaireDto;
import uz.zazu.king.lead.entity.LeadQuestionnaireEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LeadQuestionnaireMapper {

    @Mapping(target = "isActive", constant = "true")
    LeadQuestionnaireEntity toEntity(LeadQuestionnaireDto dto);

    LeadQuestionnaireDto toDto(LeadQuestionnaireEntity entity);

    void updateEntityFromDto(LeadQuestionnaireDto dto, @MappingTarget LeadQuestionnaireEntity entity);
}
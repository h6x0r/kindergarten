package uz.zazu.king.lead.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.lead.dto.LeadDto;
import uz.zazu.king.lead.entity.LeadEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LeadMapper {
    LeadMapper INSTANCE = Mappers.getMapper(LeadMapper.class);

    @Mapping(target = "isActive", constant = "true")
    LeadEntity toEntity(LeadDto dto);

    LeadDto toDto(LeadEntity entity);

    void updateEntityFromDto(LeadDto dto, @MappingTarget LeadEntity entity);
}
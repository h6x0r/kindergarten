package uz.zazu.king.lead.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.lead.dto.ChildDto;
import uz.zazu.king.lead.entity.ChildEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);

    @Mapping(target = "isActive", constant = "true")
    ChildEntity toEntity(ChildDto dto);

    ChildDto toDto(ChildEntity entity);

    void updateEntityFromDto(ChildDto dto, @MappingTarget ChildEntity entity);
}
package uz.zazu.king.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.document.dto.DocumentDto;
import uz.zazu.king.document.entity.DocumentEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(target = "isActive", constant = "true")
    DocumentEntity toEntity(DocumentDto dto);

    DocumentDto toDto(DocumentEntity entity);

    void updateEntityFromDto(DocumentDto dto, @MappingTarget DocumentEntity entity);

}
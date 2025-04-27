package uz.zazu.king.inventory.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.inventory.dto.InventoryDto;
import uz.zazu.king.inventory.entity.InventoryEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(target = "isActive", constant = "true")
    InventoryEntity toEntity(InventoryDto dto);

    InventoryDto toDto(InventoryEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(InventoryDto dto, @MappingTarget InventoryEntity entity);
}
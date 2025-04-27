package uz.zazu.king.employee.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.employee.dto.EmployeeDto;
import uz.zazu.king.employee.entity.EmployeeEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "active", constant = "true")
    EmployeeEntity toEntity(EmployeeDto dto);

    EmployeeDto toDto(EmployeeEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EmployeeDto dto, @MappingTarget EmployeeEntity entity);
}
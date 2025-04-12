package uz.zazu.king.employee.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import uz.zazu.king.employee.dto.QuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.QuestionnaireEducativeRoleDto;
import uz.zazu.king.employee.entity.QuestionnaireBusinessRoleEntity;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionnaireMapper {

    // ------------ Маппинг Business Role ------------

    @Mapping(target = "active", constant = "true")
    QuestionnaireBusinessRoleEntity toBusinessRoleEntity(QuestionnaireBusinessRoleDto dto);

    QuestionnaireBusinessRoleDto toBusinessRoleDto(QuestionnaireBusinessRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBusinessRoleEntityFromDto(QuestionnaireBusinessRoleDto dto, @MappingTarget QuestionnaireBusinessRoleEntity entity);


    // ------------ Маппинг Educative Role ------------

    @Mapping(target = "active", constant = "true")
    QuestionnaireEducativeRoleEntity toEducativeRoleEntity(QuestionnaireEducativeRoleDto dto);

    QuestionnaireEducativeRoleDto toEducativeRoleDto(QuestionnaireEducativeRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEducativeRoleEntityFromDto(QuestionnaireEducativeRoleDto dto, @MappingTarget QuestionnaireEducativeRoleEntity entity);

}
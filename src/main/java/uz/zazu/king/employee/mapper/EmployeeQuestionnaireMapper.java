package uz.zazu.king.employee.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireEducativeRoleDto;
import uz.zazu.king.employee.entity.EmployeeQuestionnaireBusinessRoleEntity;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeQuestionnaireMapper {

    // ------------ Маппинг Business Role ------------

    @Mapping(target = "isActive", constant = "true")
    EmployeeQuestionnaireBusinessRoleEntity toBusinessRoleEntity(EmployeeQuestionnaireBusinessRoleDto dto);

    EmployeeQuestionnaireBusinessRoleDto toBusinessRoleDto(EmployeeQuestionnaireBusinessRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBusinessRoleEntityFromDto(EmployeeQuestionnaireBusinessRoleDto dto, @MappingTarget EmployeeQuestionnaireBusinessRoleEntity entity);


    // ------------ Маппинг Educative Role ------------

    @Mapping(target = "isActive", constant = "true")
    QuestionnaireEducativeRoleEntity toEducativeRoleEntity(EmployeeQuestionnaireEducativeRoleDto dto);

    EmployeeQuestionnaireEducativeRoleDto toEducativeRoleDto(QuestionnaireEducativeRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEducativeRoleEntityFromDto(EmployeeQuestionnaireEducativeRoleDto dto, @MappingTarget QuestionnaireEducativeRoleEntity entity);

}
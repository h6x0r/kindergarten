package uz.zazu.king.employee.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileNannyDto;
import uz.zazu.king.employee.entity.CandidateProfileBusinessRoleEntity;
import uz.zazu.king.employee.entity.CandidateProfileEducatorRoleEntity;
import uz.zazu.king.employee.entity.CandidateProfileNannyRoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateProfileMapper {

    // ------------ Маппинг Business Role ------------

    @Mapping(target = "isActive", constant = "true")
    CandidateProfileBusinessRoleEntity toBusinessRoleEntity(CandidateProfileBusinessDto dto);

    CandidateProfileBusinessDto toBusinessRoleDto(CandidateProfileBusinessRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBusinessRoleEntityFromDto(CandidateProfileBusinessDto dto, @MappingTarget CandidateProfileBusinessRoleEntity entity);


    // ------------ Маппинг Educator Role ------------

    @Mapping(target = "isActive", constant = "true")
    CandidateProfileEducatorRoleEntity toEducativeRoleEntity(CandidateProfileEducatorDto dto);

    CandidateProfileEducatorDto toEducativeRoleDto(CandidateProfileEducatorRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEducativeRoleEntityFromDto(CandidateProfileEducatorDto dto, @MappingTarget CandidateProfileEducatorRoleEntity entity);


    // ------------ Маппинг Nanny Role ------------

    @Mapping(target = "isActive", constant = "true")
    CandidateProfileNannyRoleEntity toNannyRoleEntity(CandidateProfileNannyDto dto);

    CandidateProfileNannyDto toNannyRoleDto(CandidateProfileNannyRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNannyRoleEntityFromDto(CandidateProfileNannyDto dto, @MappingTarget CandidateProfileNannyRoleEntity entity);

}
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
import uz.zazu.king.employee.entity.CandidateApplicationBusinessRoleEntity;
import uz.zazu.king.employee.entity.CandidateApplicationEducatorRoleEntity;
import uz.zazu.king.employee.entity.CandidateApplicationNannyRoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateProfileMapper {

    // ------------ Маппинг Business Role ------------

    @Mapping(target = "isActive", constant = "true")
    CandidateApplicationBusinessRoleEntity toBusinessRoleEntity(CandidateProfileBusinessDto dto);

    CandidateProfileBusinessDto toBusinessRoleDto(CandidateApplicationBusinessRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBusinessRoleEntityFromDto(CandidateProfileBusinessDto dto, @MappingTarget CandidateApplicationBusinessRoleEntity entity);


    // ------------ Маппинг Educator Role ------------

    @Mapping(target = "isActive", constant = "true")
    CandidateApplicationEducatorRoleEntity toEducativeRoleEntity(CandidateProfileEducatorDto dto);

    CandidateProfileEducatorDto toEducativeRoleDto(CandidateApplicationEducatorRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEducativeRoleEntityFromDto(CandidateProfileEducatorDto dto, @MappingTarget CandidateApplicationEducatorRoleEntity entity);


    // ------------ Маппинг Nanny Role ------------

    @Mapping(target = "isActive", constant = "true")
    CandidateApplicationNannyRoleEntity toNannyRoleEntity(CandidateProfileNannyDto dto);

    CandidateProfileNannyDto toNannyRoleDto(CandidateApplicationNannyRoleEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNannyRoleEntityFromDto(CandidateProfileNannyDto dto, @MappingTarget CandidateApplicationNannyRoleEntity entity);

}
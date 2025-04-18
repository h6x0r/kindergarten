package uz.zazu.king.info.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.InfoLinkDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.entity.InfoEntity;
import uz.zazu.king.info.entity.InfoLinkEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InfoMapper {


    InfoDto toInfoDto(InfoEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    InfoEntity toInfoEntity(InfoDto dto);

    List<InfoDto> toInfoDtoList(List<InfoEntity> entities);

    void updateEntityFromDto(InfoDto dto, @MappingTarget InfoEntity entity);

    List<InfoEntity> toInfoEntityList(List<InfoDto> dtos);

    InfoLinkDto toInfoLinkDto(InfoLinkEntity entity);

    @InheritInverseConfiguration
    InfoLinkEntity toInfoLinkEntity(InfoLinkDto dto);

    List<InfoLinkDto> toInfoLinkDtoList(List<InfoLinkEntity> entities);

    List<InfoLinkEntity> toInfoLinkEntityList(List<InfoLinkDto> dtos);

    @Mapping(target = "moduleName", source = "moduleName")
    @Mapping(target = "tableLink", source = "tableLink")
    @Mapping(target = "infoList", source = "infoList")
    ModuleInfoDto toModuleInfoDto(String moduleName, String tableLink, List<InfoDto> infoList);

}

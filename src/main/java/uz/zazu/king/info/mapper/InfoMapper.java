package uz.zazu.king.info.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.InfoLinkDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.entity.InfoEntity;
import uz.zazu.king.info.entity.InfoLinkEntity;
import uz.zazu.king.info.entity.ModuleEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InfoMapper {

    InfoMapper INSTANCE = Mappers.getMapper(InfoMapper.class);

    InfoDto toInfoDto(InfoEntity entity);

    @InheritInverseConfiguration
    InfoEntity toInfoEntity(InfoDto dto);

    List<InfoDto> toInfoDtoList(List<InfoEntity> entities);

    List<InfoEntity> toInfoEntityList(List<InfoDto> dtos);

    InfoLinkDto toInfoLinkDto(InfoLinkEntity entity);

    @InheritInverseConfiguration
    InfoLinkEntity toInfoLinkEntity(InfoLinkDto dto);

    List<InfoLinkDto> toInfoLinkDtoList(List<InfoLinkEntity> entities);

    List<InfoLinkEntity> toInfoLinkEntityList(List<InfoLinkDto> dtos);

    @Mapping(target = "moduleName", source = "entity.moduleName")
    @Mapping(target = "tableLink", source = "entity.tableLink")
    @Mapping(target = "infoList", source = "infoList")
    ModuleInfoDto toModuleInfoDto(ModuleEntity entity, List<InfoDto> infoList);
}

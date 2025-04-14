package uz.zazu.king.lead.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import uz.zazu.king.lead.dto.LeadDto;
import uz.zazu.king.lead.entity.LeadEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LeadMapper {

    LeadMapper INSTANCE = Mappers.getMapper(LeadMapper.class);

    @Named("capitalizeName")
    static String capitalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "parentName", source = "parentName", qualifiedByName = "capitalizeName")
    LeadEntity toEntity(LeadDto dto);

    LeadDto toDto(LeadEntity entity);

    @Mapping(target = "parentName", source = "parentName", qualifiedByName = "capitalizeName")
    void updateEntityFromDto(LeadDto dto, @MappingTarget LeadEntity entity);
}
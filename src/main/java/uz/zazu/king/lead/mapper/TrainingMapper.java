package uz.zazu.king.lead.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.zazu.king.lead.dto.TrainingDto;
import uz.zazu.king.lead.entity.Training;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    TrainingDto toDto(Training entity);

    @Mapping(target = "isActive", constant = "true")
    Training toEntity(TrainingDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TrainingDto dto, @MappingTarget Training entity);
}
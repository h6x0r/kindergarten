package uz.zazu.king.lead.mapper;

import org.mapstruct.*;
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
package uz.zazu.king.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.zazu.king.security.dto.UserDto;
import uz.zazu.king.security.entity.UserEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    @Mapping(target = "isActive", constant = "true")
    UserEntity toEntity(UserDto dto, PasswordEncoder passwordEncoder);

    UserDto toDto(UserEntity entity);

    void updateEntityFromDto(UserDto dto, @MappingTarget UserEntity entity);
}
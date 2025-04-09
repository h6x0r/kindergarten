package uz.zazu.king.security.service;

import uz.zazu.king.security.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    UserDto findById(String id);

    List<UserDto> findAll();

    UserDto update(String id, UserDto userDto);

    void delete(String id);
}
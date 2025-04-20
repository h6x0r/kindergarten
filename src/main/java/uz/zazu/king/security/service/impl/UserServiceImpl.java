package uz.zazu.king.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zazu.king.security.common.exception.UserAlreadyExistException;
import uz.zazu.king.security.common.exception.UserNotFoundException;
import uz.zazu.king.security.dto.UserDto;
import uz.zazu.king.security.mapper.UserMapper;
import uz.zazu.king.security.repository.UserRepository;
import uz.zazu.king.security.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto userDto) {
        var userName = userDto.getUsername();
        if (userRepository.findByUserNameAndIsActive(userName).isPresent()) {
            throw new UserAlreadyExistException(userName);
        }
        var entity = userMapper.toEntity(userDto, passwordEncoder);
        var saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto findById(String id) {
        var result = userRepository.findActiveById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toDto(result);
    }

    @Override
    public List<UserDto> findAll() {
        var list = userRepository.findAll();
        return list.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(String id, UserDto userDto) {
        var userName = userDto.getUsername();
        if (userRepository.findByUserNameAndIsActive(userName).isPresent()) {
            throw new UserAlreadyExistException(userName);
        }

        var existing = userRepository.findActiveById(id).orElseThrow(() -> new UserNotFoundException(id));
        userMapper.updateEntityFromDto(userDto, existing);
        existing = userRepository.save(existing);
        return userMapper.toDto(existing);
    }

    @Override
    public void remove(String userName) {
        var existing = userRepository.findByUserNameAndIsActive(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));

        existing.setActive(false);
        userRepository.save(existing);
    }
}
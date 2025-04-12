package uz.zazu.king.lead.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.lead.dto.ChildDto;
import uz.zazu.king.lead.entity.ChildEntity;
import uz.zazu.king.lead.exception.ChildNotFoundException;
import uz.zazu.king.lead.mapper.ChildMapper;
import uz.zazu.king.lead.repository.ChildRepository;
import uz.zazu.king.lead.service.ChildService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final ChildMapper childMapper;

    @Override
    public ChildDto create(ChildDto childDto) {
        var entity = childMapper.toEntity(childDto);
        var saved = childRepository.save(entity);
        return childMapper.toDto(saved);
    }

    @Override
    public ChildDto findById(String id) {
        var result = childRepository.findActiveById(id);
        if (result == null) {
            throw new ChildNotFoundException(id);
        }
        return childMapper.toDto(result);
    }

    @Override
    public List<ChildDto> findAll() {
        var list = childRepository.findAll();
        return list.stream()
                .map(childMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChildDto update(String id, ChildDto childDto) {
        var existing = childRepository.findActiveById(id);
        if (existing == null) {
            throw new ChildNotFoundException(id);
        }
        childMapper.updateEntityFromDto(childDto, existing);
        existing = childRepository.save(existing);
        return childMapper.toDto(existing);
    }

    @Override
    public void delete(String id) {
        ChildEntity existing = childRepository.findActiveById(id);
        if (existing == null) {
            throw new ChildNotFoundException(id);
        }
        existing.setActive(false);
        childRepository.save(existing);
    }
}
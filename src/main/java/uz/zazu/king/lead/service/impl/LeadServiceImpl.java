package uz.zazu.king.lead.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.zazu.king.lead.dto.LeadDto;
import uz.zazu.king.lead.exception.LeadNotFoundException;
import uz.zazu.king.lead.mapper.LeadMapper;
import uz.zazu.king.lead.repository.LeadRepository;
import uz.zazu.king.lead.service.LeadService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;

    @Override
    public LeadDto create(LeadDto leadDto) {
        var entity = leadMapper.toEntity(leadDto);
        var saved = leadRepository.save(entity);
        return leadMapper.toDto(saved);
    }

    @Override
    public LeadDto findById(String id) {
        var result = leadRepository.findActiveById(id);
        if (result == null) {
            throw new LeadNotFoundException(id);
        }
        return leadMapper.toDto(result);
    }

    @Override
    public List<LeadDto> findAll() {
        var list = leadRepository.findAll();
        return list.stream()
                .map(leadMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LeadDto update(String id, LeadDto leadDto) {
        var existing = leadRepository.findActiveById(id);
        if (existing == null) {
            throw new LeadNotFoundException(id);
        }
        leadMapper.updateEntityFromDto(leadDto, existing);
        return leadMapper.toDto(existing);
    }

    @Override
    @Transactional
    public void delete(String id) {
        var existing = leadRepository.findActiveById(id);
        if (existing == null) {
            throw new LeadNotFoundException(id);
        }
        existing.setActive(false);
        leadRepository.save(existing);
    }
}
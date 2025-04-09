package uz.zazu.king.lead.service;

import uz.zazu.king.lead.dto.LeadDto;

import java.util.List;

public interface LeadService {
    LeadDto create(LeadDto leadDto);

    LeadDto findById(String id);

    List<LeadDto> findAll();

    LeadDto update(String id, LeadDto leadDto);

    void delete(String id);
}
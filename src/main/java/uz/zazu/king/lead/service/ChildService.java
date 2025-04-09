package uz.zazu.king.lead.service;

import uz.zazu.king.lead.dto.ChildDto;

import java.util.List;

public interface ChildService {
    ChildDto create(ChildDto childDto);

    ChildDto findById(String id);

    List<ChildDto> findAll();

    ChildDto update(String id, ChildDto childDto);

    void delete(String id);
}
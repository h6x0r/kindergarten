package uz.zazu.king.document.service;

import uz.zazu.king.document.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    DocumentDto create(DocumentDto documentDto);

    DocumentDto findById(String id);

    List<DocumentDto> findAll();

    DocumentDto update(String id, DocumentDto documentDto);

    void delete(String id);

}

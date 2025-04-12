package uz.zazu.king.old.document.service;

import uz.zazu.king.old.document.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    DocumentDto create(DocumentDto documentDto);

    DocumentDto findById(String id);

    List<DocumentDto> findAll();

    DocumentDto update(String id, DocumentDto documentDto);

    void delete(String id);

}

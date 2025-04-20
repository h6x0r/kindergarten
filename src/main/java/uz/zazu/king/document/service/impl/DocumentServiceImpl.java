package uz.zazu.king.document.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.document.commons.exception.DocumentNotFoundException;
import uz.zazu.king.document.dto.DocumentDto;
import uz.zazu.king.document.mapper.DocumentMapper;
import uz.zazu.king.document.repository.DocumentRepository;
import uz.zazu.king.document.service.DocumentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;


    @Override
    public DocumentDto create(DocumentDto documentDto) {
        var entity = documentMapper.toEntity(documentDto);
        var saved = documentRepository.save(entity);
        return documentMapper.toDto(saved);
    }

    @Override
    public DocumentDto findById(String id) {
        var result = documentRepository.findActiveById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        return documentMapper.toDto(result);
    }

    @Override
    public List<DocumentDto> findAll() {
        var list = documentRepository.findAll();
        return list.stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDto update(String id, DocumentDto documentDto) {
        var existing = documentRepository.findActiveById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        documentMapper.updateEntityFromDto(documentDto, existing);
        var updatedEntity = documentRepository.save(existing);
        return documentMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(String id) {
        var existing = documentRepository.findActiveById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        existing.setActive(false);
        documentRepository.save(existing);
    }
}
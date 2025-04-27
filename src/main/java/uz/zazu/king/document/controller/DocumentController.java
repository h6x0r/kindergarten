package uz.zazu.king.document.controller;

import org.springframework.web.bind.annotation.*;
import uz.zazu.king.document.dto.DocumentDto;
import uz.zazu.king.document.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public DocumentDto create(@RequestBody DocumentDto documentDto) {
        return documentService.create(documentDto);
    }

    @GetMapping("/{id}")
    public DocumentDto getById(@PathVariable String id) {
        return documentService.findById(id);
    }

    @GetMapping
    public List<DocumentDto> getAll() {
        return documentService.findAll();
    }

    @PutMapping("/{id}")
    public DocumentDto update(@PathVariable String id, @RequestBody DocumentDto documentDto) {
        return documentService.update(id, documentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        documentService.delete(id);
    }
}
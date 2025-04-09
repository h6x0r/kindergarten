package uz.zazu.king.document.commons.exception;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String id) {
        super(String.format("Document with id %s not found", id));
    }
}

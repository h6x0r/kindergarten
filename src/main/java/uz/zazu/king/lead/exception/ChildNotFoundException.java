package uz.zazu.king.lead.exception;

public class ChildNotFoundException extends RuntimeException {
    public ChildNotFoundException(String id) {
        super("Child not found with id: " + id);
    }
}
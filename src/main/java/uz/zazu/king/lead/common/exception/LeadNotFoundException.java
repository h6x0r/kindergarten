package uz.zazu.king.lead.common.exception;

public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException(String id) {
        super("Lead not found with id: " + id);
    }
}
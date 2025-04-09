package uz.zazu.king.security.common.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String id) {
        super("Role not found with id: " + id);
    }
}
package uz.zazu.king.info.exception;

public class InfoNotFoundException extends RuntimeException {
    public InfoNotFoundException() {
        super("Info not found with the specified name");
    }

    public InfoNotFoundException(String id) {
        super("Info not found with ID: " + id);
    }
}

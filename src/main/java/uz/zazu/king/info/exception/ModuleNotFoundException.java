package uz.zazu.king.info.exception;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("Module not found with the specified name");
    }

    public ModuleNotFoundException(String id) {
        super("Info not found with ID: " + id);
    }
}

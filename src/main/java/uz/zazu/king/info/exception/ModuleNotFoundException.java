package uz.zazu.king.info.exception;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("Module not found with the specified name");
    }

    public ModuleNotFoundException(String name) {
        super("Module not found with name: " + name);
    }
}

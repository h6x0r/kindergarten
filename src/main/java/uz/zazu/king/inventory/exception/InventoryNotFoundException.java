package uz.zazu.king.inventory.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String id) {
        super("Inventory not found with id: " + id);
    }
}
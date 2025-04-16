package uz.zazu.king.common.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Токен недействителен");
    }
}

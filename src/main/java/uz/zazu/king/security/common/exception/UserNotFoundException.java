package uz.zazu.king.security.common.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("Пользователь с ID/именем " + id + " не найден.");
    }
}
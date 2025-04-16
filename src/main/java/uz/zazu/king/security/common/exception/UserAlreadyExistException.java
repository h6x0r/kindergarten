package uz.zazu.king.security.common.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String name) {
        super("Пользователь с таким именем уже существует: " + name);
    }
}
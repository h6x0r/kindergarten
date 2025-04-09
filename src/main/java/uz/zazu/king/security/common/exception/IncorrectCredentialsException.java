package uz.zazu.king.security.common.exception;

public class IncorrectCredentialsException extends RuntimeException {
    public IncorrectCredentialsException() {
        super("Неверное имя пользователя или пароль");
    }
}
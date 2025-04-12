package uz.zazu.king.common.exception;

public class QuestionnaireNotFoundException extends RuntimeException {
    public QuestionnaireNotFoundException(String id) {
        super("Анкета с идентификатором не найдена: " + id);
    }
}
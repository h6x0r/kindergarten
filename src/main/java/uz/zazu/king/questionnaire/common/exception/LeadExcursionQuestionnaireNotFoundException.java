package uz.zazu.king.questionnaire.common.exception;

public class LeadExcursionQuestionnaireNotFoundException extends RuntimeException {
    public LeadExcursionQuestionnaireNotFoundException(String id) {
        super("Активная запись не найдена: " + id);
    }
}

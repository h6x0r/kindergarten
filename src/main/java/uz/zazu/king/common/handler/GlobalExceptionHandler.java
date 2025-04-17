package uz.zazu.king.common.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import uz.zazu.king.common.dto.ApiError;
import uz.zazu.king.common.exception.InvalidTokenException;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.employee.common.exception.EmployeeNotFoundException;
import uz.zazu.king.info.exception.InfoNotFoundException;
import uz.zazu.king.info.exception.ModuleNotFoundException;
import uz.zazu.king.lead.common.exception.LeadNotFoundException;
import uz.zazu.king.security.common.exception.IncorrectCredentialsException;
import uz.zazu.king.security.common.exception.UserAlreadyExistException;
import uz.zazu.king.security.common.exception.UserNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAnyException(
            Exception ex, HttpServletRequest request) {

        var apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {

        var apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler({
            InvalidTokenException.class, QuestionnaireNotFoundException.class,
            InfoNotFoundException.class, ModuleNotFoundException.class, IncorrectCredentialsException.class,
            UserAlreadyExistException.class, UserNotFoundException.class, EmployeeNotFoundException.class,
            LeadNotFoundException.class, MethodArgumentNotValidException.class
    })
    public ResponseEntity<ApiError> handleInvalidTokenException(
            RuntimeException ex, HttpServletRequest request) {

        var apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFoundException(
            NoResourceFoundException ex, HttpServletRequest request) {

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                "Запрошенный ресурс не найден",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
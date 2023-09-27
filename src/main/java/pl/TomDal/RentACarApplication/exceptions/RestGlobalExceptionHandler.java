package pl.TomDal.RentACarApplication.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE) //powoduje, że moja obsługa wyjątków jest przed obsługą domyślną
public class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {//powoduje, że wyjątki obsługiwane przez framework, bedą obsługiwane po mojemu

    private static final Map<Class<?>, HttpStatus> EXCEPTION_STATUS = Map.of(
            ConstraintViolationException.class, HttpStatus.BAD_REQUEST,
            DataIntegrityViolationException.class, HttpStatus.BAD_REQUEST,
            NonUniqueResultException.class, HttpStatus.BAD_REQUEST,
            IncorrectResultSizeDataAccessException.class, HttpStatus.BAD_REQUEST,
            NoSuchElementException.class, HttpStatus.NOT_FOUND,
            EntityNotFoundException.class, HttpStatus.NOT_FOUND,
            NotFoundException.class, HttpStatus.NOT_FOUND
    );

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(//to nadpisanie jest wygenerowane przeze mnie
            @NonNull Exception ex,
            Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request) {
        final String errorId = UUID.randomUUID().toString();
        log.error("My Exception handler: ID={}, HttpStatus={}", errorId, statusCode, ex);

        return super.handleExceptionInternal(
                ex,
                ExceptionMessage.of(errorId),//tu oryginalnie było body - podmieniłem na moją klasę ExceptionMessage
                headers,
                statusCode,
                request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception exception){ //ta metoda służy do tego, aby określić, że każdy wyjątek, który nie jest obsługiwany w żadnym innym miejscu ma być obsłużony tutaj
        return doHandle(exception, getHttpStatusFromException(exception.getClass()));
    }

    private ResponseEntity<?> doHandle(Exception exception, HttpStatus status){
        final String errorId = UUID.randomUUID().toString();
        log.error("My Exception handler: ID={}, HttpStatus={}", errorId, status, exception);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionMessage.of(errorId));
    }

    private HttpStatus getHttpStatusFromException(final Class<?> exceptionClass){
        return EXCEPTION_STATUS.getOrDefault(exceptionClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

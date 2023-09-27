package pl.TomDal.RentACarApplication.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@ControllerAdvice
public class MvcGlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS = Map.of(
            DataIntegrityViolationException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            NotFoundException.class, HttpStatus.NOT_FOUND,
            ProcessingException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            InvalidDataAccessResourceUsageException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            BindException.class, HttpStatus.BAD_REQUEST,
            MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST,
            InternalAuthenticationServiceException.class, HttpStatus.UNAUTHORIZED);

    private static final Map<Class<? extends Exception>, String> EXCEPTION_DESCRIPTION = Map.of(
            DataIntegrityViolationException.class, "Duplicated key value",
            NotFoundException.class, "Could not find a resource",
            ProcessingException.class, "Processing exception occurred",
            InvalidDataAccessResourceUsageException.class, "Invalid Data Access occurred",
            BindException.class, "Bad request",
            MethodArgumentNotValidException.class, "Bad request",
            InternalAuthenticationServiceException.class, "Authentication error occurred");

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR,
                EXCEPTION_DESCRIPTION.getOrDefault(ex.getClass(), "Unknown error occurred."));
    }

    @ExceptionHandler({DataIntegrityViolationException.class, NotFoundException.class,
            ProcessingException.class, InvalidDataAccessResourceUsageException.class,
            BindException.class, MethodArgumentNotValidException.class, InternalAuthenticationServiceException.class})
    public ModelAndView handleSpecificException(Exception ex) {
        HttpStatus status = EXCEPTION_STATUS.get(ex.getClass());
        String description = EXCEPTION_DESCRIPTION.get(ex.getClass());
        return handleExceptionInternal(ex, status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR,
                description != null ? description : "Unknown error occurred.");
    }

    private ModelAndView handleExceptionInternal(Exception ex, HttpStatus httpStatus, String description) {
        String errorId = UUID.randomUUID().toString();
        String message = String.format("%s. Error id: %s", description, errorId);
        log.error(message, ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", message);
        modelAndView.setStatus(httpStatus);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ModelAndView handleBindingErrors(BindException ex) {
        String errorId = UUID.randomUUID().toString();
        String message = String.format("Bad request for field: [%s], wrong value: [%s]. Error id: [%s]",
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null), errorId);
        log.error(message, ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", message);
        return modelAndView;
    }
}


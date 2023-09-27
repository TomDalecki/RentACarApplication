//package pl.TomDal.RentACarApplication.exceptions;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.InvalidDataAccessResourceUsageException;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Slf4j
//@ControllerAdvice
//public class MvcGlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Other exception occurred. Error id: [%s]", errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ModelAndView handleException(DataIntegrityViolationException ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Duplicated key value. Error id: [%s]", errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ModelAndView handleException(NotFoundException ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Could not find a resource. Error id: [%s]", errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//
//    @ExceptionHandler(ProcessingException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView handleException(ProcessingException ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Processing exception occurred. Error id: [%s]", errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//
//    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView handleException(InvalidDataAccessResourceUsageException ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Invalid Data Access occurred. Error id: [%s]", errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ModelAndView handleException(BindException ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Bad request for field: [%s], wrong value: [%s]. Error id: [%s]",
//            Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
//            Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null), errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ModelAndView handleException(MethodArgumentNotValidException ex) {
//        String errorId = UUID.randomUUID().toString();
//        String message = String.format("Bad request for field: [%s], wrong value: [%s]. Error id: [%s]",
//                Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
//                Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null), errorId);
//        log.error(message, ex);
//        ModelAndView modelView = new ModelAndView("error");
//        modelView.addObject("errorMessage", message);
//        return modelView;
//    }
//}

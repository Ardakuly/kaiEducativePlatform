package spring.educativeprojects.kaieducativeplatform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerAdvicer {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException resourceNotFound, WebRequest request) {
            ExceptionDetail exceptionDetail = new ExceptionDetail
                (new Date(), resourceNotFound.getMessage(), request.getDescription(true));

        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException badRequestException, WebRequest request) {
            ExceptionDetail exceptionDetail = new ExceptionDetail
                    (new Date(), badRequestException.getMessage(), request.getDescription(true));

            return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> numberFormat(NumberFormatException numberFormatException, WebRequest request) {
        ExceptionDetail exceptionDetail = new ExceptionDetail
                (new Date(), numberFormatException.getMessage(), request.getDescription(true));

        return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameFormatException.class)
    public ResponseEntity<?> nameFormat(NameFormatException nameFormatException, WebRequest request) {
        ExceptionDetail exceptionDetail = new ExceptionDetail
                (new Date(), nameFormatException.getMessage(), request.getDescription(true));

        return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }
}

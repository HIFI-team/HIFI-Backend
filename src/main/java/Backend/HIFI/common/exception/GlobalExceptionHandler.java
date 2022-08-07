package Backend.HIFI.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static Backend.HIFI.common.exception.ErrorCode.DUPLICATE_RESOURCE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException : {}", e);
        return ErrorResponse.toResponseEntity(e);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException : {}", e);
        return ErrorResponse.toResponseEntity(e);
    }

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException e) {
        log.error("InternalServerException : {}", e);
        return ErrorResponse.toResponseEntity(e);
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundExceptionException(NotFoundException e) {
        log.error("NotFoundException : {}", e);
        return ErrorResponse.toResponseEntity(e);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleAllException(Exception e) {
        log.error("Exception : {}", e);
        return ErrorResponse.toResponseEntity(BaseException.toBaseException(e));
    }
}
package Backend.HIFI.common.aop;

import Backend.HIFI.common.entity.ErrorResponse;
import Backend.HIFI.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException : {}", e);
        return new ErrorResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException : {}", e);
        return new ErrorResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNotFoundExceptionException(NotFoundException e) {
        log.error("NotFoundException : {}", e);
        return new ErrorResponse(e);
    }

    @ExceptionHandler
    protected ErrorResponse handleAllException(Exception e) {
        log.error("Exception : {}", e);
        return new ErrorResponse(BaseException.toBaseException(e));
    }
}
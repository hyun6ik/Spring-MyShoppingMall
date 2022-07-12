package hyun6ik.shoppingmall.global.exception.exceptionhandler;

import hyun6ik.shoppingmall.global.exception.BusinessException;
import hyun6ik.shoppingmall.global.exception.OrderValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Object handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(OrderValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Object handleOrderValidException(OrderValidException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }
}

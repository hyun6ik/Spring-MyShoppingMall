package hyun6ik.shoppingmall.global.exception;

import lombok.Getter;

@Getter
public class ValidException extends RuntimeException{
    private final String errorCode;
    private final String errorMessage;

    public ValidException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

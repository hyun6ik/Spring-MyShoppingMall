package hyun6ik.shoppingmall.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String prefix) {
        super(errorCode.getMessage() + prefix);
        this.errorCode = errorCode;
    }

}

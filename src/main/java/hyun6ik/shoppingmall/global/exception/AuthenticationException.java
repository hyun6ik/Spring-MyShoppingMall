package hyun6ik.shoppingmall.global.exception;

public class AuthenticationException extends BusinessException{
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}

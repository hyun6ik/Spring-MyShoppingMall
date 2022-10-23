package hyun6ik.shoppingmall.global.exception;

public class SocialLoginException extends BusinessException{
    public SocialLoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}

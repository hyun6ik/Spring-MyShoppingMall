package hyun6ik.shoppingmall.global.exception;

public class InvalidRoleAccessException extends BusinessException{


    public InvalidRoleAccessException(ErrorCode errorCode) {
        super(errorCode);
    }
}

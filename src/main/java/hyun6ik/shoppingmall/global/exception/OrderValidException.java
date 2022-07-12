package hyun6ik.shoppingmall.global.exception;

public class OrderValidException extends BusinessException{
    public OrderValidException(ErrorCode errorCode) {
        super(errorCode);
    }

    public OrderValidException(ErrorCode errorCode, String prefix) {
        super(errorCode, prefix);
    }
}

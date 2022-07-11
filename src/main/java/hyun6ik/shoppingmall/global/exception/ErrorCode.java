package hyun6ik.shoppingmall.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    ALREADY_REGISTERED_MEMBER(400, "이미 가입된 회원 입니다."),
    MISMATCHED_PASSWORD(401, "패스워드가 일치하지 않습니다."),
    LOGIN_ERROR(401, "아이디 또는 비밀번호를 확인해주세요"),

    INVALID_ADMIN_ROLE(400,"관리자 계정이 아닙니다."),

    NOT_FOUND_DELIVERY(400, "배송 정보를 찾을 수 없습니다."),
    NOT_FOUND_ITEM(400,"해당 상품을 찾을 수 업습니다."),
    NOT_FOUND_ITEM_IMAGE(400,"해당 상품의 이미지를 찾을 수 업습니다."),
    NOT_FOUND_ORDER(400,"해당 주문 내역을 찾을 수 없습니다."),
    NOT_FOUND_ORDER_ITEM(400,"해당 주문 내역의 상품을 찾을 수 없습니다."),

    NOT_UPLOAD_REP_IMAGE(400,"첫번째 상품 이미지는 필수 입력 값입니다."),

    NOT_ENOUGH_STOCK(400,"상품의 재고가 부족합니다.");

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}
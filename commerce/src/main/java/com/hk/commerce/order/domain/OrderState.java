package com.hk.commerce.order.domain;

public enum OrderState {
    //결제 전, 출고 준비중, 배송 중, 배송 완료, 주문 취소
    PAYMENT_WAITING, PREPARING, SHIPPING, DELIVERING_COMPLETED, CANCEL,
    ;

    public boolean canCancel() {
        return this.equals(PAYMENT_WAITING) || this.equals(PREPARING);
    }

    public boolean canChangeable() {
        return this.equals(PAYMENT_WAITING) || this.equals(PREPARING);
    }
}

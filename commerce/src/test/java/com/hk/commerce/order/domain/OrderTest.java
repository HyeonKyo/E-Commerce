package com.hk.commerce.order.domain;

import com.hk.commerce.product.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
* ### 기능
- 주문 발행: 사용자가 장바구니에 있는 제품을 주문할 수 있습니다.
- 순서 보기: 제품, 총 비용 및 상태와 같은 기존 주문의 세부 정보를 볼 수 있습니다.
- 주문 취소: 사용자가 기존 주문을 발송하기 전에 취소할 수 있습니다.
- 주문 내역 보기: 사용자가 이전 주문을 모두 볼 수 있습니다.
### 규칙
- 주문은 상태가 존재합니다.
- 사용자가 프로필에서 청구 및 배송 주소를 완료한 경우에만 주문할 수 있습니다.
- 상품 재고가 존재하는 경우에만 주문할 수 있습니다.
- 주문이 성사되는 순간 상품 재고가 주문 개수만큼 감소합니다.
* */
class OrderTest {

    @Test
    void 장바구니에서_주문발행() {

    }
}
package com.hk.commerce.order.domain;

import com.hk.commerce.cart.domain.Cart;
import com.hk.commerce.member.domain.Member;
import com.hk.commerce.product.domain.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    void 주문생성_회원기본주소() {
        Member member = makeMember();
        Product product = new Product("상품명", "상품 설명", 10_000,
                10, "imageURL");
        Cart cart = product.makeCart(member, 3);

        Order order = cart.makeOrder();

        assertThat(order).isNotNull();
        assertThat(order.getShippingAddress()).isEqualTo("회원 기본 주소");
    }

    @Test
    void 주문생성_다른주소() {
        Member member = makeMember();
        Product product = new Product("상품명", "상품 설명", 10_000,
                10, "imageURL");
        Cart cart = product.makeCart(member, 3);

        Order order = cart.makeOrder("다른 주소");

        assertThat(order).isNotNull();
        assertThat(order.getShippingAddress()).isEqualTo("다른 주소");
    }

    @Test
    void 주문취소_출고후_불가() {
        Order orderAfterShipping = Order.builder()
                .state(OrderState.SHIPPING)
                .shippingAddress("주소")
                .build();

        assertThatThrownBy(() -> orderAfterShipping.cancel())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("주문 취소가 불가능한 상태입니다.");
    }

    @Test
    void 주문취소_출고전_가능() {
        Order orderBeforeShipping = Order.builder()
                .state(OrderState.PREPARING)
                .shippingAddress("주소")
                .build();

        orderBeforeShipping.cancel();
        assertThat(orderBeforeShipping.getState()).isEqualTo(OrderState.CANCEL);
    }

    @Test
    void 배송지변경_출고전_가능() {
        Order orderBeforeShipping = Order.builder()
                .state(OrderState.PREPARING)
                .shippingAddress("주소")
                .build();
        String newAddress = "새 주소";

        orderBeforeShipping.changeShippingAddress(newAddress);

        assertThat(orderBeforeShipping.getShippingAddress()).isEqualTo(newAddress);
    }

    @Test
    void 배송지변경_출고후_불가() {
        Order orderAfterShipping = Order.builder()
                .state(OrderState.SHIPPING)
                .shippingAddress("주소")
                .build();
        String newAddress = "새 주소";

        assertThatThrownBy(() -> orderAfterShipping.changeShippingAddress(newAddress))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Member makeMember() {
        return Member.createJoinMember("asd123@naver.com", "Abcd1234", "이름",
                "회원 기본 주소", "010-1234-5678");
    }
}
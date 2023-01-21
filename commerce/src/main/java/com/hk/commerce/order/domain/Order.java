package com.hk.commerce.order.domain;

import com.hk.commerce.cart.domain.Cart;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private String shippingAddress;

    @Builder
    public Order(Cart cart, OrderState state, String shippingAddress) {
        this.cart = cart;
        this.state = state;
        this.shippingAddress = shippingAddress;
    }

    public void cancel() {
        if (!state.canCancel()) {
            throw new IllegalStateException("주문 취소가 불가능한 상태입니다.");
        }
        state = OrderState.CANCEL;
    }

    public void changeShippingAddress(String newAddress) {
        if (!state.canChangeable()) {
            throw new IllegalStateException("주문을 변경할 수 없습니다.");
        }
        shippingAddress = newAddress;
    }
}

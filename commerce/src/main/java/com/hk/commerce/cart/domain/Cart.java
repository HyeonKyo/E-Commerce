package com.hk.commerce.cart.domain;

import com.hk.commerce.member.domain.Member;
import com.hk.commerce.order.domain.Order;
import com.hk.commerce.order.domain.OrderState;
import com.hk.commerce.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Builder
    public Cart(Member member, Product product, int quantity) {
        this.member = member;
        this.product = product;
        this.quantity = quantity;
    }

    public Order makeOrder() {
        return makeOrder(member.getAddress());
    }

    public Order makeOrder(String shippingAddress) {
        return Order.builder()
                .cart(this)
                .state(OrderState.PAYMENT_WAITING)
                .shippingAddress(shippingAddress)
                .build();
    }
}

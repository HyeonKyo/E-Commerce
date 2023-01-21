package com.hk.commerce.order.domain;

import com.hk.commerce.cart.domain.Cart;
import com.hk.commerce.member.domain.Member;
import com.hk.commerce.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private String shippingAddress;

    private String billingAddress;
}

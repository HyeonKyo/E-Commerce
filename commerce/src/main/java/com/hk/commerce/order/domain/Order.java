package com.hk.commerce.order.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
    private Set<OrderLine> orderLines;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private String shippingAddress;

    private String billingAddress;
}

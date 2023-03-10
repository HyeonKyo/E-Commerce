package com.hk.commerce.product.domain;

import com.hk.commerce.cart.domain.Cart;
import com.hk.commerce.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"))
    private Set<Long> categoryIds;

    private String name;

    private String description;

    private long price;

    private int stock;

    private String image;

    private ProductState state;

    @Builder
    public Product(String name, String description, long price, int stock, String image) {
        this.name = name;
        this.description = description;
        setPrice(price);
        this.stock = stock;
        this.image = image;
        this.state = ProductState.SELLING;
    }

    public void editInformation(String name, String description, long price, int stock, String imageURL) {
        this.name = name;
        this.description = description;
        setPrice(price);
        this.stock = stock;
        this.image = imageURL;
    }

    public void stopSelling() {
        this.state = ProductState.STOP_SELLING;
    }

    private void setPrice(long price) {
        if (price <= 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }

    public Cart makeCart(Member member, int quantity) {
        checkEnoughStock(quantity);
        checkCanSelling();
        return Cart.builder()
                .product(this)
                .member(member)
                .quantity(quantity).build();
    }

    private void checkCanSelling() {
        if (!state.canSelling()) {
            throw new RuntimeException("품절이거나 판매 불가능한 상품입니다.");
        }
    }

    private void checkEnoughStock(int quantity) {
        if (quantity > stock) {
            throw new RuntimeException("상품 재고가 부족합니다.");
        }
    }
}

package com.hk.commerce.cart.domain;

import com.hk.commerce.member.domain.Member;
import com.hk.commerce.product.domain.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
- 사용자는 제품이 재고가 있고 구매 가능한 경우에만 장바구니에 제품을 추가할 수 있습니다.
- 사용자의 쇼핑 카트에 있는 제품의 수량은 사용 가능한 재고보다 클 수 없습니다.
- 사용자가 로그인한 경우에만 제품을 사용자 카트에 추가할 수 있습니다.
* */
class CartTest {

    @Test
    void 장바구니생성_정상() {
        Member member = makeMember();
        Product product = new Product("상품명", "상품 설명", 10_000,
                10, "imageURL");

        int quantity = 3;
        Cart cart = product.makeCart(member, quantity);

        assertThat(cart).isNotNull();
        assertThat(cart.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void 장바구니생성_재고부족() {
        int stock = 1;
        int quantity = 2;
        Member member = makeMember();
        Product product = new Product("상품명", "상품 설명", 10_000,
                stock, "imageURL");

        assertThatThrownBy(() -> product.makeCart(member, quantity))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상품 재고가 부족합니다.");
        assertThat(product.makeCart(member, stock)).isNotNull();
    }

    @Test
    void 장바구니생성_판매불가상태() {
        Member member = makeMember();
        Product product = new Product("상품명", "상품 설명", 10_000,
                10, "imageURL");
        product.stopSelling();

        assertThatThrownBy(() -> product.makeCart(member, 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("품절이거나 판매 불가능한 상품입니다.");
    }

    private static Member makeMember() {
        return Member.createJoinMember("asd123@naver.com", "Abcd1234", "이름",
                "주소", "010-1234-5678");
    }
}
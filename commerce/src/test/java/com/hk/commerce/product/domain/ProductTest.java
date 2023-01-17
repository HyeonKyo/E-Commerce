package com.hk.commerce.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        product = makeProduct();
    }

    private Product makeProduct() {
        return Product.builder()
                .name("상품명")
                .description("설명")
                .price(10_000)
                .stock(10)
                .image("asd").build();
    }

    @Test
    void 제품_생성() {
        Product newProduct = makeProduct();
        assertThat(newProduct).isNotNull();
    }

    @Test
    void 제품_정보_편집() {
        String newName = "새로운 상품명";
        String newDescription = "새로운 설명";
        int newPrice = 20_000;
        int newStock = 100;
        String newImageURL = "asdassd";

        product.editInformation(newName, newDescription, newPrice, newStock, newImageURL);

        assertThat(product.getName()).isEqualTo(newName);
        assertThat(product.getDescription()).isEqualTo(newDescription);
        assertThat(product.getPrice()).isEqualTo(newPrice);
        assertThat(product.getStock()).isEqualTo(newStock);
        assertThat(product.getImage()).isEqualTo(newImageURL);
    }

    @Test
    void 판매_중지() {
        product.stopSelling();

        assertThat(product.getState()).isEqualTo(ProductState.STOP_SELLING);
    }
}

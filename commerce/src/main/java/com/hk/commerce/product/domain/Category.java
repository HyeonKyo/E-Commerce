package com.hk.commerce.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product_category")
public class Category {
    @Id
    private Long id;

    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}

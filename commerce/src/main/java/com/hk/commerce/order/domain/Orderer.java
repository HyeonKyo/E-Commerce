package com.hk.commerce.order.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Orderer {
    @Column(name = "orderer_id")
    private Long memberId;

    @Column(name = "orderer_name")
    private String name;
}

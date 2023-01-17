package com.hk.commerce.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Password {
    @Column(name = "password")
    private String value;

    public Password(String value) {
        verifyValue(value);
        this.value = value;
    }

    private void verifyValue(String value) {
        //TODO 정규표현식으로 변경하기
        if (value.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
        if (value.equals(value.toLowerCase())) {
            throw new IllegalArgumentException("비밀번호는 대문자 1개를 포함해야 합니다.");
        }
        if (value.equals(value.toUpperCase())) {
            throw new IllegalArgumentException("비밀번호는 소문자 1개를 포함해야 합니다.");
        }
    }
}

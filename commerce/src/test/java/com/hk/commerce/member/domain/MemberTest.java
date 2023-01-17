package com.hk.commerce.member.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
* ### 기능
- 회원가입: 새 사용자는 이메일과 비밀번호로 로그인하며, 회원 가입 시에 이름, 주소, 전화번호와 함께 가입합니다. 또한 일반 사용자인지 사업자인지 구분하는 정보도 주어져야 합니다.
- 로그인: 기존 사용자가 전자 메일 및 암호를 제공하여 계정에 로그인할 수 있습니다.
- 로그아웃: 기존 사용자가 자신의 계정에서 로그아웃할 수 있습니다.
- 권한 관리: 관리자, 사업자, 일반 회원, 비회원으로 4가지 권한이 있다.

### 규칙
- 사용자의 전자 메일은 고유해야 하며 다른 계정을 등록하는 데 사용할 수 없습니다.
- 사용자가 회원 가입할 때, 이메일로 확인 메일이 전송되어야 하고, 인증 이후부터 회원 권한을 가지게 됩니다.
- 사용자의 암호는 8자 이상이어야 하며 하나 이상의 대문자와 소문자 하나와 숫자 하나를 포함해야 합니다.
- 데이터베이스에 저장하기 전에 사용자의 암호를 해시해야 합니다.
- 사용자가 계정에 로그인하려면 먼저 전자 메일 주소를 확인해야 합니다.
- 사용자는 주문하기 전에 주소와 전화 번호를 제공해야 합니다.
- 권한에 따라 접근 가능한 기능이 다르다.
* */

class MemberTest {

    @Test
    void 회원가입() {
        String email = "abcd123@naver.com";
        String password = "Abcd1234";
        String name = "이름";
        String address = "서울특별시 노원구 상계동";
        String phoneNumber = "010-1234-5678";

        Member member = Member.createJoinMember(email, password, name, address, phoneNumber);
        assertThat(member).isNotNull();
    }

    @Test
    void 암호규칙_8자이상() {
        String password = "Abcd123";
        assertThatThrownBy(() -> new Password(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8자 이상이어야 합니다.");
    }

    @Test
    void 암호규칙_대문자1개포함() {
        String password = "abcd1234";
        assertThatThrownBy(() -> new Password(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 대문자 1개를 포함해야 합니다.");
    }

    @Test
    void 암호규칙_소문자1개포함() {
        String password = "ABCD1234";
        assertThatThrownBy(() -> new Password(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 소문자 1개를 포함해야 합니다.");
    }
}
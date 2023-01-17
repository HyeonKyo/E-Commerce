package com.hk.commerce.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private Password password;

    private String address;

    private String phoneNumber;

    private Role role;

    private boolean emailVerified;

    private Member(String email, String password, String name,
                  String address, String phoneNumber) {
        setEmail(email);
        setPassword(password);
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        role = Role.ROLE_NOT_VERIFIED;
    }

    public static Member createJoinMember(String email, String password, String name,
                                          String address, String phoneNumber, String role) {
        return new Member(email, password, name, address, phoneNumber);
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = new Password(password);
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

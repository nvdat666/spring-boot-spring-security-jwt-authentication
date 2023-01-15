package vn.datnv.springjwt.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER ("User"),
    ROLE_ADMIN ("Admin"),
    ROLE_ANONYMOUS("Anonymous");

    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }

}

package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDTO {

    private String username;
    private String passwordHash;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

}
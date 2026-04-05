package com.catering.app.account.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountLoginRequest {

    @NotBlank(message = "{validation.accountLoginRequest.email.notBlank}")
    @Email(message = "{validation.accountLoginRequest.email.email}")
    private String email;

    @NotBlank(message = "{validation.accountLoginRequest.password.notBlank}")
    @Size(min = 8, max = 72, message = "{validation.accountLoginRequest.password.size}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

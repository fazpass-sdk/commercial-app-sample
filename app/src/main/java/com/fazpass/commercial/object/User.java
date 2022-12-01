package com.fazpass.commercial.object;

import androidx.annotation.NonNull;

public class User extends com.fazpass.trusted_device.User {
    private final String pin;

    public User(@NonNull String email, @NonNull String phone, @NonNull String name, @NonNull String idCard, @NonNull String address, String pin) {
        super(email, phone, name, idCard, address);
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }
}

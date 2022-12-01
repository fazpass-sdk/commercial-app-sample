package com.fazpass.commercial.helper;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.fazpass.commercial.object.User;

public class Storage {
    private static final String key_user_is_logged_in = "key_user_is_logged_in";
    private static final String key_user_email = "key_user_email";
    private static final String key_user_phone = "key_user_phone";
    private static final String key_user_name = "key_user_name";
    private static final String key_user_id_card = "key_user_id_card";
    private static final String key_user_address = "key_user_address";
    private static final String key_user_is_use_finger = "key_user_is_use_finger";
    private static final String key_user_pin = "key_user_pin";

    public static void saveUser(Context context, User user) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key_user_is_logged_in, true);
        editor.putString(key_user_email, user.getEmail());
        editor.putString(key_user_phone, user.getPhone());
        editor.putString(key_user_name, user.getName());
        editor.putString(key_user_id_card, user.getIdCard());
        editor.putString(key_user_address, user.getAddress());
        editor.putBoolean(key_user_is_use_finger, User.isUseFinger());
        editor.putString(key_user_pin, user.getPin());
        editor.apply();
    }

    @Nullable
    public static User getUser(@Nullable Context context) {
        if (context == null) return null;

        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        if (sharedPref.getBoolean(key_user_is_logged_in, false)) {
            String email = sharedPref.getString(key_user_email, null);
            String phone = sharedPref.getString(key_user_phone, null);
            String name = sharedPref.getString(key_user_name, null);
            String idCard = sharedPref.getString(key_user_id_card, null);
            String address = sharedPref.getString(key_user_address, null);
            boolean isUseFinger = sharedPref.getBoolean(key_user_is_use_finger, false);
            String pin = sharedPref.getString(key_user_pin, null);

            User user = new User(email, phone, name, idCard, address, pin);
            User.setIsUseFinger(isUseFinger);
            return user;
        }

        return null;
    }

    public static void logout(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}

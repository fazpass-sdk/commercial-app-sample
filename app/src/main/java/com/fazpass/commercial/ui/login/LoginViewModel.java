package com.fazpass.commercial.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.commercial.R;
import com.fazpass.commercial.helper.LOGIN_TYPE;
import com.fazpass.commercial.helper.Storage;
import com.fazpass.commercial.object.User;
import com.fazpass.trusted_device.CROSS_DEVICE;
import com.fazpass.trusted_device.Fazpass;
import com.fazpass.trusted_device.FazpassTd;
import com.fazpass.trusted_device.TRUSTED_DEVICE;
import com.fazpass.trusted_device.TrustedDeviceListener;

import java.util.ArrayList;

public class LoginViewModel extends ViewModel {

    private LoginFragment fragment;
    private AlertDialog dialog;
    private LOGIN_TYPE loginType;

    public void initialize(LoginFragment fragment) {
        this.fragment = fragment;

        User user = Storage.getUser(fragment.requireContext());
        if (user != null) {
            Navigation.findNavController(fragment.requireView())
                    .navigate(R.id.action_loginFragment_to_profileFragment);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.requireContext());
        builder.setTitle("Logging in")
                .setMessage("\nPlease wait while we try to log you in...\n")
                .setCancelable(false);
        dialog = builder.create();
    }

    public void login(String login, String pin) {
        if (login.isEmpty() || pin.isEmpty()) {
            failedLogin("Please fill in all of this form.");
            return;
        }

        loginType = null;
        try {
            Double.parseDouble(login);
            loginType = LOGIN_TYPE.phone;
        } catch (NumberFormatException ignored) {}
        if (loginType==null && login.contains("@")) {
            loginType = LOGIN_TYPE.email;
        }
        if (loginType==null) {
            failedLogin("Input is neither email nor phone number.");
            return;
        }

        User u;
        if (loginType==LOGIN_TYPE.email) {
            u = new User(login, "-", login.split("@")[0],"","", pin);
        }
        else {
            u = new User("null@null.com", login, "","","", pin);
        }
        User.setIsUseFinger(false);

        dialog.show();
        Fazpass.check(fragment.getContext(), u.getEmail(), u.getPhone(), pin, new TrustedDeviceListener<FazpassTd>() {
            @Override
            public void onSuccess(FazpassTd o) {
                dialog.dismiss();
                if (o.td_status == TRUSTED_DEVICE.TRUSTED) {
                    successLogin(u);
                }
                else {
                    toConfirmOptions(u, o.cd_status);
                }
            }

            @Override
            public void onFailure(Throwable err) {
                dialog.dismiss();
                failedLogin("Failed to initialize login. Check your internet and try again.");
            }
        });
    }

    private void successLogin(User u) {
        Storage.saveUser(fragment.requireContext(), u);

        Navigation.findNavController(fragment.requireView())
                .navigate(R.id.action_loginFragment_to_profileFragment);
    }

    private void toConfirmOptions(User u, CROSS_DEVICE cd_status) {
        ArrayList<String> list = new ArrayList<>();
        list.add(u.getEmail());
        list.add(u.getPhone());
        list.add(u.getName());
        list.add(u.getIdCard());
        list.add(u.getAddress());
        list.add(u.getPin());

        Bundle args = new Bundle();
        args.putStringArrayList("ARGS_USER", list);
        args.putBoolean("ARGS_CD_IS_AVAILABLE", cd_status.equals(CROSS_DEVICE.AVAILABLE));
        args.putString("ARGS_LOGIN_TYPE", loginType.toString());
        Navigation.findNavController(fragment.requireView())
                .navigate(R.id.action_loginFragment_to_confirmLoginFragment, args);
    }

    private void failedLogin(String errorMessage) {
        fragment.showErrorMessage(errorMessage);
    }
}
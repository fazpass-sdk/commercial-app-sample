package com.fazpass.commercial.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fazpass.commercial.R;
import com.fazpass.commercial.helper.Storage;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private TextView errorTxt;

    private String errorMessageArg;
    private String emailArg;
    private String phoneArg;
    private String pinArg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if (getArguments() != null) {
            errorMessageArg = getArguments().getString("error_message");
            emailArg = getArguments().getString("ARGS_EMAIL");
            phoneArg = getArguments().getString("ARGS_PHONE");
            pinArg = getArguments().getString("ARGS_PIN");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        errorTxt = v.findViewById(R.id.error_txt);
        EditText emailInput = v.findViewById(R.id.email_input);
        EditText phoneInput = v.findViewById(R.id.phone_input);
        EditText pinInput = v.findViewById(R.id.pin_input);

        if (errorMessageArg != null)
            showErrorMessage(errorMessageArg);
        if (emailArg != null)
            emailInput.setText(emailArg);
        if (phoneArg != null)
            phoneInput.setText(phoneArg);
        if (pinArg != null)
            pinInput.setText(pinArg);

        Button loginBtn = v.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(view -> {
            errorTxt.setVisibility(View.GONE);
            mViewModel.login(
                    emailInput.getText().toString(),
                    phoneInput.getText().toString(),
                    pinInput.getText().toString());
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mViewModel.initialize(this);
    }

    public void showErrorMessage(String message) {
        errorTxt.setText(message);

        if (errorTxt.getVisibility() == View.GONE)
            errorTxt.setVisibility(View.VISIBLE);
    }
}
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

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private TextView errorTxt;

    private String errorMessageArg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if (getArguments() != null) {
            errorMessageArg = getArguments().getString("error_message");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        errorTxt = v.findViewById(R.id.error_txt);
        EditText loginInput = v.findViewById(R.id.login_input);
        EditText pinInput = v.findViewById(R.id.pin_input);

        if (errorMessageArg != null)
            showErrorMessage(errorMessageArg);

        Button loginBtn = v.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(view -> {
            errorTxt.setVisibility(View.GONE);
            mViewModel.login(
                    loginInput.getText().toString(),
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
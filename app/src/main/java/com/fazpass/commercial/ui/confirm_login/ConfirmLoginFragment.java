package com.fazpass.commercial.ui.confirm_login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fazpass.commercial.R;

import java.util.Objects;

public class ConfirmLoginFragment extends Fragment {

    private ConfirmLoginViewModel mViewModel;
    private TextView errorTxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfirmLoginViewModel.class);

        Bundle args = getArguments();
        mViewModel.setUser(Objects.requireNonNull(args).getStringArrayList("ARGS_USER"));
        mViewModel.setCDAvailable(args.getBoolean("ARGS_CD_IS_AVAILABLE"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_login, container, false);

        TextView greetingTxt = v.findViewById(R.id.greeting_txt);
        greetingTxt.setText(getString(R.string.greeting_message, mViewModel.getUser().getName()));

        Button cdNotifBtn = v.findViewById(R.id.cd_notif_btn);
        if (!mViewModel.isCDAvailable()) cdNotifBtn.setEnabled(false);
        cdNotifBtn.setOnClickListener(view -> mViewModel.crossDeviceNotificationOption());
        Button smsVerBtn = v.findViewById(R.id.sms_ver_btn);
        smsVerBtn.setOnClickListener(view -> mViewModel.smsVerificationOption());
        Button miscallVerBtn = v.findViewById(R.id.miscall_ver_btn);
        miscallVerBtn.setOnClickListener(view -> mViewModel.miscallVerificationOption());
        Button emailVerBtn = v.findViewById(R.id.email_ver_btn);
        emailVerBtn.setOnClickListener(view -> mViewModel.emailVerificationOption());

        errorTxt = v.findViewById(R.id.error_txt2);

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

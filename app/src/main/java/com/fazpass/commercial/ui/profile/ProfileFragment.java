package com.fazpass.commercial.ui.profile;

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
import com.fazpass.commercial.helper.Storage;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        mViewModel.setUser(Storage.getUser(requireContext()));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView emailText = v.findViewById(R.id.email_text);
        emailText.setText(mViewModel.getUser().getEmail());
        TextView phoneText = v.findViewById(R.id.phone_text);
        phoneText.setText(mViewModel.getUser().getPhone());

        Button logoutBtn = v.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(v1 -> mViewModel.logout());

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mViewModel.initialize(this);
    }
}
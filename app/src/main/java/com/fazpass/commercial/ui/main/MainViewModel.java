package com.fazpass.commercial.ui.main;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.fazpass.commercial.LoginActivity;

public class MainViewModel extends ViewModel {
    private MainFragment fragment;

    public void initialize(MainFragment fragment) {
        this.fragment = fragment;
    }

    public void openProfileDialog() {
        Intent intent = new Intent(fragment.requireActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        fragment.startActivity(intent);
    }
}
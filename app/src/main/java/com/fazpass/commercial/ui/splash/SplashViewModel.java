package com.fazpass.commercial.ui.splash;

import android.os.Handler;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.commercial.R;

public class SplashViewModel extends ViewModel {

    public void startCountdown(SplashFragment fragment) {
        new Handler().postDelayed(afterCountdown(fragment), 3000L);
    }

    private Runnable afterCountdown(SplashFragment fragment) {
        return () -> Navigation.findNavController(fragment.requireView())
                .navigate(R.id.action_splashFragment_to_mainFragment);
    }
}
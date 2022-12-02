package com.fazpass.commercial.ui.profile;

import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import com.fazpass.commercial.R;
import com.fazpass.commercial.helper.Storage;
import com.fazpass.commercial.object.User;
import com.fazpass.trusted_device.Fazpass;

public class ProfileViewModel extends ViewModel {

    private User user;
    private ProfileFragment fragment;

    public void initialize(ProfileFragment profileFragment) {
        this.fragment = profileFragment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void logout() {
        Fazpass.removeDevice(fragment.requireActivity().getApplicationContext());
        Storage.logout(fragment.requireContext());

        NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_profileFragment_to_loginFragment);
    }
}
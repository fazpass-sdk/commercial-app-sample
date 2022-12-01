package com.fazpass.commercial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fazpass.trusted_device.Fazpass;
import com.fazpass.trusted_device.MODE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fazpass.initialize(this, getResources().getString(R.string.merchant_key), MODE.STAGING);
        Fazpass.launchedFromNotificationRequirePin(this, getIntent());
        Fazpass.requestPermission(this);
    }
}
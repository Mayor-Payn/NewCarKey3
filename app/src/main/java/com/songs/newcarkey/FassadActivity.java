package com.songs.newcarkey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.yandex.mobile.ads.common.InitializationListener;

import java.util.Arrays;
import java.util.List;

public class FassadActivity extends AppCompatActivity {

    ImageButton startMain, policyPriv, exitApp;
    private long timeExit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fassad);

        //Инициализация рекламы
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        //Тестовое устройство
        List<String> testDeviseIds = Arrays.asList("81E818539026DB92B57AF296BC44595B", "2BCD865A9C01B334D6B88D7BFC8C31C0");
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(testDeviseIds).build();
        MobileAds.setRequestConfiguration(configuration);

        //реклама яндекс
        com.yandex.mobile.ads.common.MobileAds.initialize(this, new InitializationListener() {
            @Override
            public void onInitializationCompleted() { }});

        //показ баннера
        if (ConfigParse.adsBanner) { showBanner(); }
        //Загрузка интера для его показа при нажатии старт арр
        Ads.loadAd(FassadActivity.this);

        //Обработка нажатия перехода на маин активити
        startMain = findViewById(R.id.start_app);
        startMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FassadActivity.this, MainActivity.class);
                startActivity(intent);
                Ads.showInterstitial(FassadActivity.this);  //Показ интера
                Ads.loadAd(FassadActivity.this);            // и сразу загрузка нового интера
            }
        });

        //Обработка нажатия политика конф
        policyPriv = findViewById(R.id.policy_prived);
        policyPriv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigation.showPolicy(FassadActivity.this);
            }
        });

        //Обработка нажатия выход из приложения
        exitApp = findViewById(R.id.exit_app);
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }//////////////////////////////////////////////////

    //загрузка баннер
    private void showBanner () {
        LinearLayout linearLayout = findViewById(R.id.banner_layout);
        Ads.loadBanner(this, linearLayout);
    }

    //Кнопка назад или выход после двух нажатий
    public void onBackPressed () {
        if ((System.currentTimeMillis() - timeExit) > 2000) {
            Toast.makeText(this, getString(R.string.app_exit), Toast.LENGTH_SHORT).show();
            timeExit = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}

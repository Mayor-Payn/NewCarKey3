package com.songs.newcarkey;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.List;


public class SplashActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Conekt.isNetworkAvailable(SplashActivity.this)) {
                    new Loading().execute(getResources().getString(R.string.url_json));
                } else {
                    new AlertDialog.Builder(SplashActivity.this)
                            .setTitle(getResources().getString(R.string.splash_warning))
                            .setMessage(getResources().getString(R.string.splash_no_internet))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.splash_restart), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.splash_close), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).create().show();
                            //.show();
                }
            }
        }, 4000);
    }

    public class Loading extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return Conekt.getJSONString(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                //Собираем данные из json
                JSONObject jo = new JSONObject(s);
                ConfigParse.adsBanner = jo.getBoolean("adsban");
                ConfigParse.adsInter = jo.getBoolean("adsint");

                //Переход в следующею активность
                Intent intent = new Intent(SplashActivity.this, FassadActivity.class);
                startActivity(intent);
                finish();

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

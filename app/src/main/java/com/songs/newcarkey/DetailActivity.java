package com.songs.newcarkey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView, playStartStop, playPedal;
    Toolbar toolbar;
    String nameAvto;
    int imageKey;
    int soundZapusk, soundGaz, soundFinal;
    MediaPlayer playerZapusk, playerGaz, playerFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //показ баннера
        if (ConfigParse.adsBanner) { showBanner(); }

        textView = findViewById(R.id.tv_title);
        imageView = findViewById(R.id.detail_key);
        toolbar = findViewById(R.id.toolBar_detail);
        playStartStop = (ImageView) findViewById(R.id.start_stop);
        playPedal = (ImageView) findViewById(R.id.pedal_gaza);

        //Получаем данные из адаптера
        nameAvto = getIntent().getStringExtra("name");
        imageKey = getIntent().getIntExtra("imgdetail", 0);
        soundZapusk = getIntent().getIntExtra("soundzapusk", 0);
        soundGaz = getIntent().getIntExtra("soundgaz", 0);
        soundFinal = getIntent().getIntExtra("soundfinal", 0);
        textView.setText(nameAvto);
        //imageView.setImageResource(imageKey);

        Picasso.get().load(imageKey).into (imageView);

        //Glide.with(this).load(imageKey).into(imageView);

        //Задаем плееру нужные звуки
        playerZapusk = MediaPlayer.create(this, soundZapusk);
        playerGaz = MediaPlayer.create(this, soundGaz);
        playerFinal = MediaPlayer.create(this, soundFinal);

        imageClickStartStop();
        imageClickPedal();

        //Панель управления нижней навигации
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_back:
                        playerZapusk.stop();
                        playerGaz.stop();
                        playerFinal.stop();
                        finish();
                        break;
                    case R.id.bottom_more:
                        BottomNavigation.showMore(DetailActivity.this);
                        break;
                    case R.id.bottom_share:
                        BottomNavigation.showShare(DetailActivity.this);
                        break;
                }
                return true;
            }
        });



    }/////////////////////////////////

    //загрузка баннер
    private void showBanner () {
        LinearLayout linearLayout = findViewById(R.id.banner_layout);
        Ads.loadBanner(this, linearLayout);
    }

    //Обработка клика вкл и выкл звука двигателя
    public void imageClickStartStop() {
        playStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playerZapusk.isPlaying()) {
                    playerZapusk.start();
                    playStartStop.setImageResource(R.drawable.stop);
                    playerZapusk.setLooping(true); //Зацикливает звук
                } else {
                    if (playerZapusk.isPlaying()){
                        playStartStop.setImageResource(R.drawable.play);
                        playerZapusk.pause();
                        playerGaz.pause();
                        playerFinal.pause();
                        playPedal.setImageResource(R.drawable.pedal_off);
                    }
                }
            }
        });
    }

    //Обработка нажатия педали газа
    public void imageClickPedal() {
        playPedal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){ //Если педаль газа нажата
                    if (playerZapusk.isPlaying()){
                        playPedal.setImageResource(R.drawable.pedal_on);
                        playerGaz.start();
                        playerGaz.setLooping(true);// Зацикливание звука газа
                        if (!playerZapusk.isPlaying()){
                            playerGaz.pause();
                            playerFinal.pause();
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) { //Если педаль газа отпущена
                    if (playerGaz.isPlaying() && playerZapusk.isPlaying()){
                        playPedal.setImageResource(R.drawable.pedal_off);
                        playerGaz.pause();
                        playerGaz.seekTo(0);
                        playerFinal.start();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        playerZapusk.stop();
        playerGaz.stop();
        playerFinal.stop();
        finish();
    }
}

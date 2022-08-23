package com.songs.newcarkey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    List<ItemAvto> avtoList = new ArrayList<>();
    Adapter adapter;
    private DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //показ баннера
        if (ConfigParse.adsBanner) { showBanner(); }
        Ads.loadAd(MainActivity.this);

        //Контент
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        avtoList.add(new ItemAvto("Alfa Romeo 4C",R.drawable.alfarom,R.drawable.alfaromeo_4c_sp,R.raw.holost_sport,R.raw.alfa_romeo,R.raw.final_final));
        avtoList.add(new ItemAvto("Aston Martin AM-RB 001",R.drawable.aston_amrb,R.drawable.aston_amrb_sp,R.raw.holost_maserati,R.raw.aston,R.raw.final_aston));
        avtoList.add(new ItemAvto("Audi R8 V10",R.drawable.audi_r,R.drawable.audi_r_sp,R.raw.holost_supercar,R.raw.audi,R.raw.final_audi));
        avtoList.add(new ItemAvto("Audi ТТ", R.drawable.audi_tt, R.drawable.audi_tt_sp,R.raw.holost_sport,R.raw.honda,R.raw.final_honda));
        avtoList.add(new ItemAvto("Bentley Continental", R.drawable.bentley, R.drawable.bentley_sp,R.raw.holost_subaru,R.raw.mersedes_tu,R.raw.final_mersedes_tu));
        avtoList.add(new ItemAvto("BMW i8", R.drawable.bmw_i, R.drawable.bmw_i_sp,R.raw.holost_sport,R.raw.bmw_i,R.raw.final_final));
        avtoList.add(new ItemAvto("BMW Z4",R.drawable.bmw_z,R.drawable.bmw_z_sp,R.raw.holost_maserati,R.raw.bmw,R.raw.final_audi));
        avtoList.add(new ItemAvto("Bugatti Chiron",R.drawable.bug_chiron,R.drawable.bug_chiron_sp,R.raw.holost_buga,R.raw.buga,R.raw.final_buga));
        avtoList.add(new ItemAvto("Bugatti Divo",R.drawable.bug_div,R.drawable.bug_divo_sp,R.raw.holost_supersport,R.raw.jeepsrt,R.raw.final_jeepsrt));
        avtoList.add(new ItemAvto("Bugatti La Voiture Noire",R.drawable.bug_la,R.drawable.bug_la_voiture_sp,R.raw.holost_buga,R.raw.porshe,R.raw.final_porshe));

        avtoList.add(new ItemAvto("Bugatti Veyron", R.drawable.bug_veiron, R.drawable.bug_veyron_sp,R.raw.holost_buga,R.raw.subaru,R.raw.final_subaru));
        avtoList.add(new ItemAvto("Chevrolet Camaro", R.drawable.camaro, R.drawable.camaro_sp,R.raw.holost_camaro,R.raw.camaro,R.raw.final_camaro));
        avtoList.add(new ItemAvto("Chevrolet Camaro ZL1",R.drawable.camaro_zl,R.drawable.camaro_zl_sp,R.raw.holost_camaro_z,R.raw.camaro_z,R.raw.final_camaro_z));
        avtoList.add(new ItemAvto("Citroen GT Concept",R.drawable.sitroen,R.drawable.sitroen_gt_sp,R.raw.holost_supersport,R.raw.jaguar,R.raw.final_final));
        avtoList.add(new ItemAvto("Dodge Challenger", R.drawable.dodge, R.drawable.dodge_sp,R.raw.holost_camaro,R.raw.dodge,R.raw.final_dodge));
        avtoList.add(new ItemAvto("Ferrari 430 Scuderia", R.drawable.ferrari, R.drawable.ferrari_scud_sp,R.raw.holost_supercar,R.raw.ferrari,R.raw.final_ferrari));
        avtoList.add(new ItemAvto("Ferrari SF90",R.drawable.ferrari_sf,R.drawable.ferrari_sf_sp,R.raw.holost_supercar_tu,R.raw.lambo_tu,R.raw.final_lambo_tu));
        avtoList.add(new ItemAvto("Ford Mustang", R.drawable.ford_must, R.drawable.mustang_sp,R.raw.holost_mustang,R.raw.camaro,R.raw.final_camaro));
        avtoList.add(new ItemAvto("Ford GT",R.drawable.ford_gt,R.drawable.ford_gt_sp,R.raw.holost_maserati,R.raw.aston,R.raw.final_aston));
        avtoList.add(new ItemAvto("Honda Civic Type", R.drawable.honda, R.drawable.honda_sp,R.raw.holost_sport,R.raw.honda,R.raw.final_honda));

        avtoList.add(new ItemAvto("Koenigsegg Agera", R.drawable.koen_agera, R.drawable.keni_agera_sp,R.raw.holost_supersport,R.raw.koen_agera,R.raw.final_koen_agera));
        avtoList.add(new ItemAvto("Koenigsegg Agera RS",R.drawable.koen_agera_rs,R.drawable.koeni_agera_rs_sp,R.raw.holost_supercar,R.raw.koen_tri,R.raw.final_final));
        avtoList.add(new ItemAvto("Koenigsegg Regera",R.drawable.koen_regera,R.drawable.koenig_regera_sp,R.raw.holost_koen_tu,R.raw.koen_tu,R.raw.final_final));
        avtoList.add(new ItemAvto("Koenigsegg Jesko",R.drawable.koen_jasko,R.drawable.koenig_jesko_sp,R.raw.holost_supercar,R.raw.ferrari,R.raw.final_ferrari));
        avtoList.add(new ItemAvto("Lamborghini Aventador", R.drawable.lambo_ave, R.drawable.lambo_ave_sp,R.raw.holost_supersport,R.raw.lambo_tu,R.raw.final_lambo_tu));
        avtoList.add(new ItemAvto("Lamborghini Centenario",R.drawable.lambo_cente,R.drawable.lambo_cente_sp,R.raw.holost_supercar,R.raw.jeepsrt,R.raw.final_jeepsrt));
        avtoList.add(new ItemAvto("Lexus LFA ", R.drawable.lexus, R.drawable.lexus_sp,R.raw.holost_sport,R.raw.lexus,R.raw.final_lexus));
        avtoList.add(new ItemAvto("Maserati Granturismo", R.drawable.maseraty, R.drawable.maserati_gran_sp,R.raw.holost_supercar_tu,R.raw.jaguar,R.raw.final_final));
        avtoList.add(new ItemAvto("Maserati MC12",R.drawable.maserati_mc,R.drawable.maserati_mc_sp,R.raw.holost_maserati,R.raw.maserati,R.raw.final_maserati));
        avtoList.add(new ItemAvto("McLaren",R.drawable.mclaren,R.drawable.mclaren_sp,R.raw.holost_supercar_tu,R.raw.jaguar_tu,R.raw.final_jaguar_tu));

        avtoList.add(new ItemAvto("Mercedes-Benz SLS AMG", R.drawable.mersedes, R.drawable.mersedes_sp,R.raw.holost_supercar,R.raw.mersedes,R.raw.final_mersedes));
        avtoList.add(new ItemAvto("Nissan GT-R", R.drawable.nissan, R.drawable.nissan_sp,R.raw.holost_supercar_tu,R.raw.audi,R.raw.final_audi));
        avtoList.add(new ItemAvto("Porsche 911 GT3", R.drawable.porche, R.drawable.porche_sp,R.raw.holost_sport,R.raw.porshe,R.raw.final_porshe));
        avtoList.add(new ItemAvto("Pagani Zonda",R.drawable.pagani_zonda,R.drawable.pagani_zonda_sp,R.raw.holost_supersport,R.raw.maserati,R.raw.final_maserati));
        avtoList.add(new ItemAvto("Rolls Royce GHOST", R.drawable.rolsroyse, R.drawable.rolsroys_sp,R.raw.holost_supercar,R.raw.alfa_romeo,R.raw.final_final));
        avtoList.add(new ItemAvto("Subaru Impreza WRX STI", R.drawable.subaru, R.drawable.subaru_sp,R.raw.holost_subaru,R.raw.subaru,R.raw.final_subaru));
        avtoList.add(new ItemAvto("Toyota GR Supra", R.drawable.tayota, R.drawable.tayota_sp,R.raw.holost_sport,R.raw.aston,R.raw.final_aston));
        avtoList.add(new ItemAvto("Volkswagen Golf R", R.drawable.volkswagen, R.drawable.volkswagen_sp,R.raw.holost_sport_tu,R.raw.golf,R.raw.final_golf));
        adapter = new Adapter(avtoList, this);
        recyclerView.setAdapter(adapter);

        //Панель управления нижней навигации
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_back:
                        //загрузка интера
                        //Ads.loadAd(MainActivity.this);
                        finish();
                        break;
                    case R.id.bottom_more:
                        BottomNavigation.showMore(MainActivity.this);
                        break;
                    case R.id.bottom_share:
                        BottomNavigation.showShare(MainActivity.this);
                        break;
                }
                return true;
            }
        });

        //Управление шторкой навигации
        drawer = findViewById(R.id.driver_layout);
        toolbar = findViewById(R.id.toolBar);
        if (getSupportActionBar() != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        //Управление шторкой
        NavigationView nv = findViewById(R.id.left_navigation);
        nv.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.open_driwer, R.string.close_driwer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }///////////////////////////////////////////////////////

    //загрузка баннер
    private void showBanner () {
        LinearLayout linearLayout = findViewById(R.id.banner_layout);
        Ads.loadBanner(this, linearLayout);
    }

    //Кнопки шторки навигации
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bottom_more:
                BottomNavigation.showMore(MainActivity.this);
                break;
            case R.id.bottom_rate:
                BottomNavigation.naviRate(MainActivity.this);
                break;
            case R.id.bottom_mail:
                BottomNavigation.showMail(MainActivity.this);
                break;
            case R.id.bottom_policy:
                BottomNavigation.showPolicy(MainActivity.this);
                break;
            case R.id.bottom_share:
                BottomNavigation.showShare(MainActivity.this);
                break;
            case R.id.bottom_exit:
                System.exit(0);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Скрывает шторку и возврыщается в фассад активити
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            //загрузка интера
            //Ads.loadAd(this);
            finish();
        }
    }
}

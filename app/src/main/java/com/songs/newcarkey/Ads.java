package com.songs.newcarkey;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;


public class Ads {

    public static InterstitialAd mInterstitialAdmob;
    public static AdRequest adRequestAdmob;

    public static com.yandex.mobile.ads.interstitial.InterstitialAd mInterstitialYandex;
    public static com.yandex.mobile.ads.common.AdRequest adRequestYandex;

    //баннер загрузка и настройка
    public static void loadBanner (Context context, LinearLayout linearLayout){
        if (context.getResources().getConfiguration().locale.getLanguage().equalsIgnoreCase("ru")) {
            final BannerAdView bannerAdView = new BannerAdView(context);
            linearLayout.addView(bannerAdView);
            bannerAdView.setAdUnitId(context.getResources().getString(R.string.idBannerYandex));
            bannerAdView.setAdSize(com.yandex.mobile.ads.banner.AdSize.stickySize(com.yandex.mobile.ads.banner.AdSize.FULL_SCREEN.getWidth()));
            bannerAdView.loadAd(new com.yandex.mobile.ads.common.AdRequest.Builder().build());
            bannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
                @Override
                public void onAdLoaded() { bannerAdView.setVisibility(View.VISIBLE);}
                @Override
                public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {bannerAdView.setVisibility(View.GONE);}
                @Override
                public void onAdClicked() {}
                @Override
                public void onLeftApplication() {}
                @Override
                public void onReturnedToApplication() {}
                @Override
                public void onImpression(@Nullable ImpressionData impressionData) {}});
        }else {
            final AdView adView = new AdView(context);
            linearLayout.addView(adView);
            adView.setAdUnitId(context.getResources().getString(R.string.id_ads_banner));
            adView.setAdSize(AdSize.SMART_BANNER);
            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    adView.setVisibility(View.GONE); //Не показывать баннер если что то пошло не так
                }
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    adView.setVisibility(View.VISIBLE); //показ баннера если все ок
                }}); }
    }


    //Загрузка интера
    public static void loadAd(Context context) {
        if (context.getResources().getConfiguration().locale.getLanguage().equalsIgnoreCase("ru")) {
            mInterstitialYandex = new com.yandex.mobile.ads.interstitial.InterstitialAd(context);
            mInterstitialYandex.setAdUnitId(context.getResources().getString(R.string.idInterYandex));
            adRequestYandex = new com.yandex.mobile.ads.common.AdRequest.Builder().build();
            mInterstitialYandex.loadAd(adRequestYandex);
            mInterstitialYandex.setInterstitialAdEventListener(new InterstitialAdEventListener() {
                @Override
                public void onAdLoaded() { }
                @Override
                public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {}
                @Override
                public void onAdShown() {}
                @Override
                public void onAdDismissed() {}
                @Override
                public void onAdClicked() {}
                @Override
                public void onLeftApplication() {}
                @Override
                public void onReturnedToApplication() {}
                @Override
                public void onImpression(@Nullable ImpressionData impressionData) {
                }});
        } else {
            adRequestAdmob = new AdRequest.Builder().build();
            InterstitialAd.load(context, context.getResources().getString(R.string.id_ads_inter), adRequestAdmob, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAdmob = interstitialAd;
                    mInterstitialAdmob.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            mInterstitialAdmob = null; }
                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            mInterstitialAdmob = null; }
                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAdmob = null;
                        }}); }
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    mInterstitialAdmob = null;
                    String error = String.format("domain: %s, code: %d, message: %s", loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                }});
        }
    }


    //Показ интера
    public static  void showInterstitial(Context context) {
        if (context.getResources().getConfiguration().locale.getLanguage().equalsIgnoreCase("ru")) {
            if (ConfigParse.adsInter) {
                if (mInterstitialYandex.isLoaded()) {
                    mInterstitialYandex.show();
                }
            }
        } else {
            if (ConfigParse.adsInter) {
                if (mInterstitialAdmob != null) {
                    mInterstitialAdmob.show((Activity) context);
                    loadAd(context);
                }
            }
        }
    }







}

package com.songs.newcarkey;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class BottomNavigation {

    //Кнопка другие приложения
    public static void showMore (Context context) {

        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(context.getResources().getString(R.string.url_store_play))));
    }

    //Кнопка политика конфиденциальности
    public static void showPolicy (Context context) {

        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(context.getResources().getString(R.string.driwer_privacy_policy_url))));
    }

    //Кнопка ценить приложение
    public static void naviRate (final Context context){
        final String appPackageName = context.getPackageName();
        final Dialog dialogDaNet = new Dialog(context);
        dialogDaNet.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDaNet.setContentView(R.layout.dialog_rate);
        Button buttonYes = dialogDaNet.findViewById(R.id.dialog_yes);
        Button buttonNo = dialogDaNet.findViewById(R.id.dialog_no);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.dialog_yes:
                        try {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (ActivityNotFoundException a) {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                        dialogDaNet.dismiss();
                        break;

                    case R.id.dialog_no:
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        String uriText = "mailto:" + Uri.encode(context.getResources().getString(R.string.mail_developerman)) + "?subject=" + Uri.encode(context.getResources().getString(R.string.dialog_new_mail));
                        Uri uri = Uri.parse(uriText);
                        intent.setData(uri);
                        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.dialog_title_mail)));
                        dialogDaNet.dismiss();
                        break;
                }
            }
        };
        buttonYes.setOnClickListener(onClickListener);
        buttonNo.setOnClickListener(onClickListener);
        dialogDaNet.show();
    }

    //Кнопка отправить письмо разработчику
    public static void showMail (Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(context.getResources().getString(R.string.mail_developerman)) + "?subject=" + Uri.encode(context.getResources().getString(R.string.dialog_new_mail));
        Uri uri = Uri.parse(uriText);
        intent.setData(uri);
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.dialog_title_mail)));
    }

    //Кнопка поделиться с другими
    public static void showShare (final Context context){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Text");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        context.startActivity(Intent.createChooser(intent, "Share using"));
    }
}

package com.songs.newcarkey;

import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemAvto> avtoList;
    Context context;

    public Adapter(List<ItemAvto> avtoList, Context context) {
        this.avtoList = avtoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_avto, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final ItemAvto avto = avtoList.get(i);
        viewHolder.tv_nameAvto.setText(avto.getNameAvto());
        //viewHolder.iv_avtoSp.setImageResource(avto.getImgAvtoSp());
        ImageView cardView = viewHolder.iv_avtoSp;
        Glide.with(context).load((Integer) avto.getImgAvtoSp()).into(cardView);

        //Перенос данных в детаил активность
        ((ViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", avto.getNameAvto());
                intent.putExtra("imgdetail", avto.getImgKey());
                intent.putExtra("soundzapusk", avto.getSoundZapusk());
                intent.putExtra("soundgaz", avto.getSoundGaz());
                intent.putExtra("soundfinal", avto.getSoundFinal());
                context.startActivity(intent);


                Ads.showInterstitial(context); //показ интера
                Ads.loadAd(context); //Загрузка нового интера
            }
        });
    }

    @Override
    public int getItemCount() {
        return avtoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_nameAvto;
        public ImageView iv_avtoSp;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nameAvto = itemView.findViewById(R.id.name_avto_recycler);
            iv_avtoSp = itemView.findViewById(R.id.key_avto_recyclerbig);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

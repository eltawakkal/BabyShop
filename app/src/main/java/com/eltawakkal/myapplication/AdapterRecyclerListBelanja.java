package com.eltawakkal.myapplication;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by eltawakkal on 6/4/17.
 */

class AdapterRecyclerListBelanja extends RecyclerView.Adapter<AdapterRecyclerListBelanja.ViewHolder>{

    String[] namaP, hargaP;
    int[] imgP;

    Context context;
    int lastPosition = -1;

    public AdapterRecyclerListBelanja(String[] namaP, String[] hargaP, int[] imgP, Context context) {
        this.namaP = namaP;
        this.hargaP = hargaP;
        this.imgP = imgP;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_list_belanja, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtNamaProd.setText(namaP[position]);
        holder.txtHargaProd.setText(hargaP[position]);
        holder.imgProduk.setImageResource(imgP[position]);

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return namaP.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBeli, imgProduk;
        TextView txtNamaProd, txtHargaProd;

        public ViewHolder(View itemView) {
            super(itemView);

            imgProduk = (ImageView) itemView.findViewById(R.id.imgProduk);
            imgBeli = (ImageView) itemView.findViewById(R.id.imgAddToChart);

            txtNamaProd = (TextView) itemView.findViewById(R.id.txtNamaProduk);
            txtHargaProd = (TextView) itemView.findViewById(R.id.txtHargaProduk);

        }
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}

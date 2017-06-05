package com.eltawakkal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import static android.R.attr.data;

/**
 * Created by eltawakkal on 6/4/17.
 */

class  AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder>{

    String[] namaP, hargaP;
    int[] imgP;

    MainActivity mainActivity;

    Context context;
    int lastPosition = -1;

    public AdapterRecycler(String[] namaP, String[] hargaP, int[] imgP, Context context) {
        this.namaP = namaP;
        this.hargaP = hargaP;
        this.imgP = imgP;
        this.context = context;
        mainActivity = new MainActivity();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtNamaProd.setText(namaP[position]);
        holder.txtHargaProd.setText(hargaP[position]);
        holder.imgProduk.setImageResource(imgP[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder pesan = new AlertDialog.Builder(context);

                LinearLayout ly = new LinearLayout(context);
                ly.setPadding(20, 20, 20, 20);
                ly.setOrientation(LinearLayout.VERTICAL);

                ImageView imgProd = new ImageView(context);
                TextView txtHarga = new TextView(context);

                txtHarga.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                txtHarga.setPadding(10, 10, 10, 10);

                imgProd.setImageResource(imgP[position]);
                txtHarga.setText(namaP[position]);

                ly.addView(imgProd, 0);
                ly.addView(txtHarga, 1);

                pesan.setView(ly)
                        .setCancelable(false)
                        .setPositiveButton("Oke", null).create().show();
            }
        });

        holder.imgBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHelper.totalHargaBarang+=Integer.parseInt(hargaP[position]);
                DataHelper.barang.add(namaP[position]);
                DataHelper.harga_barang.add(hargaP[position]);
                DataHelper.img_barang.add(imgP[position]);
            }
        });

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

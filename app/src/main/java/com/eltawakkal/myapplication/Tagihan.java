package com.eltawakkal.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tagihan extends AppCompatActivity {

    RecyclerView mRecyclerList;
    AdapterRecyclerListBelanja adapterRecycler;

    private String[] namaP;
    private String[] hargaP;
    int[] imgP;

    EditText editBayar;
    Button btnProses;
    TextView txtTotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagihan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Pembayaran");

        addData();

        adapterRecycler = new AdapterRecyclerListBelanja(namaP, hargaP, imgP, getApplicationContext());

        editBayar = (EditText) findViewById(R.id.editBayar);
        btnProses = (Button) findViewById(R.id.btnProses);
        txtTotalHarga = (TextView) findViewById(R.id.txtTotalHarga);
        mRecyclerList = (RecyclerView) findViewById(R.id.mRecyclerList);

        txtTotalHarga.setText("Rp."+DataHelper.totalHargaBarang);

        mRecyclerList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false));
        mRecyclerList.setAdapter(adapterRecycler);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(editBayar.getText().toString()) < DataHelper.totalHargaBarang){
                    Toast.makeText(Tagihan.this, "Uang Anda Kurang", Toast.LENGTH_SHORT).show();
                    editBayar.selectAll();
                    editBayar.findFocus();
                } else {
                    Toast.makeText(Tagihan.this, "Selamat, Barang Anda Akan Segera Di kirim", Toast.LENGTH_LONG).show();
                    deleteAllCart();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllCart();
            }
        });
    }

    public void deleteAllCart(){
        DataHelper.harga_barang.clear();
        DataHelper.barang.clear();
        DataHelper.img_barang.clear();
        finish();
    }

    private void addData() {
        namaP = new String[DataHelper.barang.size()];
        hargaP = new String[DataHelper.harga_barang.size()];
        imgP = new int[DataHelper.img_barang.size()];

        for (int i=0; i<namaP.length; i++){
            namaP[i] = DataHelper.barang.get(i);
            hargaP[i] = DataHelper.harga_barang.get(i);
            imgP[i] = DataHelper.img_barang.get(i);
        }

    }

}

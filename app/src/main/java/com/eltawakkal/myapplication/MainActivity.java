package com.eltawakkal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AdapterRecycler adapter;
    RecyclerView mRecycler;
    Context context;

    String[] namaP, hargaP;
    int[] imgP = {R.drawable.baju_kodok, R.drawable.bib, R.drawable.body, R.drawable.bodysuit,
            R.drawable.bodysuit_1, R.drawable.dress, R.drawable.dress_1, R.drawable.hat,
            R.drawable.kaos, R.drawable.pijama};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;

        addData();

        adapter = new AdapterRecycler(namaP, hargaP, imgP, context);

        mRecycler = (RecyclerView) findViewById(R.id.mRecycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(context));
        mRecycler.setAdapter(adapter);

    }

    private void addData() {
        namaP = new String[10];
        hargaP = new String[10];

        namaP[0] = "Baju Kodok";
        namaP[1] = "Baju BIB";
        namaP[2] = "Baju Gendut Love";
        namaP[3] = "Baju Fit Love";
        namaP[4] = "Baju Fit 2";
        namaP[5] = "Baju Rok Indah";
        namaP[6] = "Baju Rok Beauty";
        namaP[7] = "Topi Imut Indah";
        namaP[8] = "Kaos Kaki Lucu";
        namaP[9] = "Baju Tidur";

        hargaP[0] = "20000";
        hargaP[1] = "15000";
        hargaP[2] = "32000";
        hargaP[3] = "22500";
        hargaP[4] = "30100";
        hargaP[5] = "43000";
        hargaP[6] = "12400";
        hargaP[7] = "51000";
        hargaP[8] = "33000";
        hargaP[9] = "26700";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_keranjang) {
            if (DataHelper.barang.size()==0){
                Toast.makeText(context, "Silahkan Belaja Dahulu :)", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, Tagihan.class));
            }
            return true;
        } else if (id == R.id.action_delet_cart){
            DataHelper.totalHargaBarang=0;
            DataHelper.img_barang.clear();
            DataHelper.barang.clear();
            DataHelper.harga_barang.clear();
        } else if (id == R.id.action_profile){
            AlertDialog.Builder pesan = new AlertDialog.Builder(this);
            pesan
                    .setIcon(R.drawable.baby_boy)
                    .setTitle("Tentang Saya")
                    .setMessage("Nama:\nreplace_with_your_name\nNim:\nYour_nim\n" +
                            "Mata Kuliah:\nPemrograman Bergeran\nDosen:\nDosen_name_here")
                    .setPositiveButton("Oke", null)
                    .setCancelable(false)
                    .create().show();
        }

        return super.onOptionsItemSelected(item);
    }
}

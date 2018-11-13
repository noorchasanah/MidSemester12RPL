package com.example.rplrus24.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.rplrus24.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus24.midsemester12rpl.database.MahasiswaModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class detail_group extends AppCompatActivity {
    ImageView imageView;
    TextView txtnama;
    TextView tvDeskripsi;
    FloatingActionButton fab;
    boolean flag = true;
    String nama;
    String Deskripsi;
    String Gambar;
    Button btn_trailer;
    MahasiswaHelper mahasiswaHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_group);
        imageView = findViewById(R.id.img_photo);
        txtnama = findViewById(R.id.txtuser);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        btn_trailer = findViewById(R.id.btn_trailer);
        fab = findViewById(R.id.fab);
        mahasiswaHelper = new MahasiswaHelper(detail_group.this);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            nama = bundle.getString("nama");
            Deskripsi = bundle.getString("deskripsi");
            Gambar = bundle.getString("gambar");
            txtnama.setText(nama);
            tvDeskripsi.setText(Deskripsi);
            Log.d("gambarku", "onCreate: " + Gambar);
            Glide.with(detail_group.this).load(Gambar).into(imageView);

            btn_trailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/watch?v=xLCn88bfW1o"));
                    startActivity(webIntent);
                }
            });
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
                    if (flag) {
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_circle_black_24dp));
                        mahasiswaHelper.open();
                        mahasiswaHelper.beginTransaction();
                        MahasiswaModel mahasiswaModel = new MahasiswaModel(nama, Deskripsi, Gambar);
                        mahasiswaHelper.insertTransaction(mahasiswaModel);
                        mahasiswaHelper.setTransactionSuccess();
                        mahasiswaHelper.endTransaction();
                        mahasiswaHelper.close();
                        flag = false;
                    } else if (!flag) {
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_black_24dp));
                        flag = true;
                    }
                }
            });
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class myfavasyntak extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean suksesLoad = true;
            mahasiswaHelper.open();
            mahasiswaHelper.beginTransaction();
            MahasiswaModel model = new MahasiswaModel(nama, Deskripsi, Gambar);
            mahasiswaHelper.insertTransaction(model);
            mahasiswaHelper.setTransactionSuccess();
            mahasiswaHelper.endTransaction();
            mahasiswaHelper.close();
            //Toast.makeText(getApplicationContext(), "tersimpan", Toast.LENGTH_SHORT).show();
            return suksesLoad;
        }

        @Override
        protected void onPostExecute(Boolean suksesLoad) {
            //fab.setEnabled(false);
            super.onPostExecute(suksesLoad);
        }
    }
}



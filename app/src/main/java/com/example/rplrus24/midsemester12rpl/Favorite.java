package com.example.rplrus24.midsemester12rpl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rplrus24.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus24.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {

    private MahasiswaHelper mahasiswaHelper;
    private LinearLayoutManager Layout;
    recyclerview rview;
    private ArrayList<MahasiswaModel> models;
    ModelAdapter adapter;
    LinearLayout recycleviewdata;
    TextView textdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        textdata = findViewById(R.id.textdata);
        recycleviewdata = findViewById(R.id.recycleview);
        Layout = new LinearLayoutManager(Favorite.this);
        rview.setLayoutManager(Layout);

        if (mahasiswaHelper.getAllData() == null) {
            rview.setVisibility(View.VISIBLE);
            textdata.setVisibility(View.VISIBLE);
        } else if (mahasiswaHelper.getAllData() != null) {
            textdata.setVisibility(View.GONE);
            mahasiswaHelper = new MahasiswaHelper(getApplicationContext());
            mahasiswaHelper.open();
            models = mahasiswaHelper.getAllData();
            adapter = new ModelAdapter(getApplicationContext(), models);
            rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rview.setAdapter(adapter);
            Log.d("database_failed", "onCreate:" + "Success");

        }

    }
}


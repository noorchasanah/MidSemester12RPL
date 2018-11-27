package com.example.rplrus24.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView rview;
    private ArrayList<MahasiswaModel> mahasiswaModelArrayList;
    private ModelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        rview = (RecyclerView) findViewById(R.id.rv_favorite);
        mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaModelArrayList = new ArrayList<MahasiswaModel>();
        mahasiswaModelArrayList = mahasiswaHelper.getAllData();
        ModelAdapter adapter = new ModelAdapter(getApplicationContext(),mahasiswaModelArrayList);
        rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rview.setAdapter(adapter);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
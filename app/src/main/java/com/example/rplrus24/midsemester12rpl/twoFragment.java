package com.example.rplrus24.midsemester12rpl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.rplrus24.midsemester12rpl.database.MahasiswaHelper;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class twoFragment extends Fragment {
    public ArrayList<Result> itemObjectArrayList;
    item_object itemObject;
    RecyclerView recyclerView;
    MahasiswaHelper mahasiswaHelper;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.Rview_up);
        mahasiswaHelper = new MahasiswaHelper(view.getContext());
        load_data_upcoming();
        return view;
    }
    private void load_data_upcoming() {
        json_api service = retrofitclientinstance.getRetrofitInstance().create(json_api.class);
        Call<jsonrespon> call = service.getJsonNowPlaying();
        call.enqueue(new Callback<jsonrespon>() {
            @Override
            public void onResponse(Call<jsonrespon> call, Response<jsonrespon> response) {
                jsonrespon jsonRespond = response.body();
                itemObjectArrayList = new ArrayList<>(Arrays.asList(jsonRespond.getResults()));
                Log.d("responku", "onResponse: " + jsonRespond.getResults());
                recyclerAdapterRetro adapter = new recyclerAdapterRetro(view.getContext(),itemObjectArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<jsonrespon> call, Throwable t) {
                Log.d("responku", "onFailure: gagal");
            }
        });
    }
}
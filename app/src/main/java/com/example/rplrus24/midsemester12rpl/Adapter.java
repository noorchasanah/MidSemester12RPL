package com.example.rplrus24.midsemester12rpl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import static android.app.Notification.EXTRA_TEXT;
import static android.content.Intent.ACTION_SEND;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<item_object> moviesList;
    Context context;
    String url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public  ImageView gambar;
        public Button btnklik;
        public Button btnshare;
        public Button btnhapus;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtnama);
            gambar = (ImageView) view.findViewById(R.id.image1);
            btnklik = (Button) view.findViewById(R.id.btndetail);
            btnshare = (Button) view.findViewById(R.id.btnshare);
            btnhapus = (Button)view.findViewById(R.id.btnhapus);
        }
    }

    public Adapter(List<item_object> moviesList,Context context) {
        Log.d("jumlah data", "Adapter: "+moviesList.size());
        this.context=context;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recyclerview,parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final item_object movie = moviesList.get(position);
        holder.title.setText(movie.getNama());
        Glide.with(context)
                .load(url+movie.getGambar())
                .into(holder.gambar);
        holder.btnklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = movie.getNama();
                final String gambar = url+ movie.getGambar();
                final  String deskripsi = movie.getDeskripsi();
                Intent intent = new Intent(context.getApplicationContext(),detail_group.class);
                intent.putExtra("nama",nama);
                intent.putExtra("gambar",gambar);
                intent.putExtra("deskripsi",deskripsi);
                context.startActivity(intent);
            }
        });
        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sedIntent = new Intent();
                sedIntent.setAction(ACTION_SEND);
                sedIntent.putExtra(EXTRA_TEXT,"This is my text to send.");
                sedIntent.setType("Text/plain");
                context.startActivity(sedIntent);
            }
        });
        Log.d("TAG", "getItemCount: ");
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

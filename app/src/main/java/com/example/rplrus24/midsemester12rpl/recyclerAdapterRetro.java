package com.example.rplrus24.midsemester12rpl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.app.Notification.EXTRA_TEXT;
import static android.content.Intent.ACTION_SEND;

public class recyclerAdapterRetro extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Result> itemObjectArrayList;
    Context context;

    public recyclerAdapterRetro(Context context, ArrayList<Result> itemObjectArrayList) {
        this.itemObjectArrayList = itemObjectArrayList;
        this.context = context;
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recyclerview,parent, false);

        return new Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, final int position) {
        final Result movie = itemObjectArrayList.get(position);
        holder.title.setText(movie.getTitle());
        Glide.with(context)
                .load(only_url.url + movie.getPosterPath())
                .into(holder.gambar);
        holder.btnklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = movie.getTitle();
                final String gambar = movie.getPosterPath();
                final String deskripsi = movie.getOverview();
                Intent intent = new Intent(context.getApplicationContext(), detail_group.class);
                intent.putExtra("nama", nama);
                intent.putExtra("gambar", only_url.url +gambar);
                intent.putExtra("deskripsi", deskripsi);
                context.startActivity(intent);
            }
        });
        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sedIntent = new Intent();
                sedIntent.setAction(ACTION_SEND);
                sedIntent.putExtra(EXTRA_TEXT, "This is my text to send.");
                sedIntent.setType("Text/plain");
                context.startActivity(sedIntent);
            }
        });
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setCancelable(true);
                builder.setMessage("Confrim that you want delete this book?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                itemObjectArrayList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemChanged(position,itemObjectArrayList.size());
                            }
                        });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return itemObjectArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView gambar;
        public Button btnklik;
        public Button btnshare;
        public Button btnhapus;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtnama);
            gambar = (ImageView) view.findViewById(R.id.image1);
            btnklik = (Button) view.findViewById(R.id.btndetail);
            btnshare = (Button) view.findViewById(R.id.btnshare);
            btnhapus = (Button) view.findViewById(R.id.btnhapus);
        }
    }
}
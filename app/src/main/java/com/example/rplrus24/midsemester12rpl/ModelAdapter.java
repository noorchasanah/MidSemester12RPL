package com.example.rplrus24.midsemester12rpl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rplrus24.midsemester12rpl.database.DatabaseHelper;
import com.example.rplrus24.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

import static android.app.Notification.EXTRA_TEXT;
import static android.content.Intent.ACTION_SEND;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.Holder>{
    public ArrayList<MahasiswaModel> mahasiswaModelArrayList;
    Context context;

    public ModelAdapter(Context applicationContext, ArrayList<MahasiswaModel> models) {
        this.context = context;
        this.mahasiswaModelArrayList = mahasiswaModelArrayList;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview, parent, false);
        return  new ModelAdapter.Holder(layoutView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final MahasiswaModel model = mahasiswaModelArrayList.get(position);
        Glide.with(context)
                .load(model.getUrl())
                .into(holder.gambar);
        holder.title.setText(mahasiswaModelArrayList.get(position).getName());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = mahasiswaModelArrayList.get(position).getName();
                final String gambar = mahasiswaModelArrayList.get(position).getNim();
                final  String deskripsi = mahasiswaModelArrayList.get(position).getUrl();
                final  String id = String.valueOf(mahasiswaModelArrayList.get(position).getId());
                Log.d("TAG", "deskripsi: "+ deskripsi);;
                Intent i = new Intent(context.getApplicationContext(),detail_group.class);
                i.putExtra("nama",nama);
                i.putExtra("gambar",gambar);
                i.putExtra("deskripsi",deskripsi);
                context.startActivity(i);
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
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setCancelable(true);
                builder.setMessage("are you sure to delete this item?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                        databaseHelper.deleteEntry(model.getId());
                        mahasiswaModelArrayList.remove(position);
                        databaseHelper.deleteEntry(position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
            });

    }

    @Override
    public int getItemCount() {
        return mahasiswaModelArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView gambar;
        private TextView title;
        private Button btndetail;
        private Button btnshare;
        private Button btnhapus;

        public Holder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.img_photo);
            title = (TextView) itemView.findViewById(R.id.title);
            btndetail = (Button) itemView.findViewById(R.id.btndetail);
            btnshare = (Button) itemView.findViewById(R.id.btnshare);
            btnhapus = (Button) itemView.findViewById(R.id.btnhapus);

        }
    }
}

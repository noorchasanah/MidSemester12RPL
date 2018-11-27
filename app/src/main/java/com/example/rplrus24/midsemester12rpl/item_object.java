package com.example.rplrus24.midsemester12rpl;

import android.widget.ImageView;

public class item_object {

    private String Nama;
    private String Gambar;
    private String Deskripsi;
    private String Trailer;
    private static String PosterPath;


    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }


    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }


    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String trailer) {
        Trailer = trailer;
    }

    public static String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }
}

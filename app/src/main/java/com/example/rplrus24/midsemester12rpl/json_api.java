package com.example.rplrus24.midsemester12rpl;

import retrofit2.Call;
import retrofit2.http.GET;

public interface json_api {
    @GET("now_playing?api_key=93222d58dcb1ecaa81d38a0e2b26da4f")
    Call<jsonrespon> getJsonNowPlaying();
}

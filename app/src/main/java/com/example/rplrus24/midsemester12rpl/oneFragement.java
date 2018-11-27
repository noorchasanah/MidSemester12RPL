package com.example.rplrus24.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rplrus24.midsemester12rpl.database.MahasiswaHelper;

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

public class oneFragement extends Fragment {
    public ArrayList<item_object> itemObjectArrayList;
    item_object itemObject;
    RecyclerView recyclerView;
    MahasiswaHelper mahasiswaHelper;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one_fragement, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.Rview_now);
        mahasiswaHelper = new MahasiswaHelper(rootView.getContext());
        new datafragment().execute();
        return rootView;
    }
    @SuppressLint("StaticFieldLeak")
    public class datafragment extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;

            try {
                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=93222d58dcb1ecaa81d38a0e2b26da4f";
                System.out.println(url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("hasil json ", "onPostExecute: " + jsonObject.toString());

            if (jsonObject != null) {
                try {
                    itemObjectArrayList = new ArrayList<>();
                    JSONArray aktualData = jsonObject.getJSONArray("results");
                    for (int x = 0; x < aktualData.length(); x++) {
                        item_object item_object = new item_object();
                        item_object.setNama(aktualData.getJSONObject(x).getString("title"));
                        item_object.setGambar(aktualData.getJSONObject(x).getString("poster_path"));
                        item_object.setDeskripsi(aktualData.getJSONObject(x).getString("overview"));
                        itemObjectArrayList.add(item_object);
                    }
                    Adapter adapter = new Adapter(itemObjectArrayList, rootView.getContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(adapter);
//                    recycle_view.setAdapter(adapterAllTipe);
//                    recycle_view.setNestedScrollingEnabled(false);
//                    recycle_view.setVisibility(View.VISIBLE);
//                    recycle_view.setVisibility(View.GONE);
                } catch (Exception ignored) {
                    Log.d("lalaku", "onPostExecute: "+ignored.toString());
                }
            } else {
            }
            super.onPostExecute(jsonObject);
        }
    }

}

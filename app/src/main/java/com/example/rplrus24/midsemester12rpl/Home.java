package com.example.rplrus24.midsemester12rpl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_Layout);
        tabLayout.addTab(tabLayout.newTab().setText("Now Playing"));
        tabLayout.addTab(tabLayout.newTab().setText("Up Coming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final pageAdapter adapter = new pageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.menu:
                moveTaskToBack(true);
                SharedPreferences myPrefs = getSharedPreferences("isi",MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Home.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            case R.id.fab:
                Intent intent1 = new Intent(this,Favorite.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
//    public ArrayList<item_object> itemObjectArrayList = new ArrayList<item_object>();
//    RecyclerView recycle_view;
//    item_object data2;
//    private ModelAdapter adapter;
//    private int visibility;
//    private LinearLayoutManager LinierLayoutManager;
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.logout, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch ((item.getItemId())) {
//            case R.id.menu:
//                moveTaskToBack(true);
//                SharedPreferences myPrefs = getSharedPreferences("isi",MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.apply();
//                Intent intent = new Intent(Home.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            case R.id.fab:
//                Intent intent1 = new Intent(this,Favorite.class);
//                startActivity(intent1);
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adapter);
//        recycle_view = findViewById(R.id.recycle_view);
//        //prepareMovieData();
//        new LogData().execute();
//        recycle_view.setLayoutManager(new LinearLayoutManager(Home.this));
//        Adapter adapter = new Adapter(itemObjectArrayList,Home.this);
//        recycle_view.setAdapter(adapter);
//    }
//
//    public void setLayoutManager(LinearLayoutManager layoutManager) {
//        this.LinierLayoutManager= layoutManager;
//    }
//
//    public void setVisibility(int visibility) {
//        this.visibility = visibility;
//    }
//
//    public void setAdapter(ModelAdapter adapter) {
//        this.adapter = adapter;
//    }
//
//    @SuppressLint("StaticFieldLeak")
//    public class LogData extends AsyncTask<Void, Void, JSONObject> {
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected JSONObject doInBackground(Void... params) {
//            JSONObject jsonObject;
//
//            try {
//                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=93222d58dcb1ecaa81d38a0e2b26da4f";
//                System.out.println(url);
//                DefaultHttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpGet = new HttpGet(url);
//                HttpResponse httpResponse = httpClient.execute(httpGet);
//                HttpEntity httpEntity = httpResponse.getEntity();
//                InputStream inputStream = httpEntity.getContent();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(
//                        inputStream, "iso-8859-1"
//                ), 8);
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line).append("\n");
//                }
//                inputStream.close();
//                String json = stringBuilder.toString();
//                jsonObject = new JSONObject(json);
//            } catch (Exception e) {
//                jsonObject = null;
//            }
//            return jsonObject;
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            Log.d("hasil json ", "onPostExecute: " + jsonObject.toString());
//
//            if (jsonObject != null) {
//                try {
//                    itemObjectArrayList = new ArrayList<>();
//                    JSONArray aktualData = jsonObject.getJSONArray("results");
//                    for (int x = 0; x < aktualData.length(); x++) {
//                        item_object item_object = new item_object();
//                        item_object.setNama(aktualData.getJSONObject(x).getString("title"));
//                        item_object.setGambar(aktualData.getJSONObject(x).getString("poster_path"));
//                        item_object.setDeskripsi(aktualData.getJSONObject(x).getString("overview"));
//                        itemObjectArrayList.add(item_object);
//                    }
//                    Adapter adapter = new Adapter(itemObjectArrayList,Home.this);
//                    recycle_view.setHasFixedSize(true);
//                    recycle_view.setLayoutManager(new LinearLayoutManager(recycle_view.getContext(), LinearLayoutManager.VERTICAL, false));
//                    recycle_view.setNestedScrollingEnabled(false);
//                    recycle_view.setVisibility(View.VISIBLE);
//                    recycle_view.setAdapter(adapter);
////                    recycle_view.setAdapter(adapterAllTipe);
////                    recycle_view.setNestedScrollingEnabled(false);
////                    recycle_view.setVisibility(View.VISIBLE);
////                    recycle_view.setVisibility(View.GONE);
//                } catch (Exception ignored) {
//                    Log.d("lalaku", "onPostExecute: "+ignored.toString());
//                }
//            } else {
//            }
//            super.onPostExecute(jsonObject);
//        }
//    }
////    private void prepareMovieData() {
////        item_object movie1 = new item_object();
////        movie1.setNama("natasha");
////        movie1.setGambar("http://cdn2.tstatic.net/tribunnews/foto/bank/images/natasha-wilona-main-di-sinetron-anak-sekolahan_20170214_150908.jpg");
////        movie1.setDeskripsi("Natasha Wilona, artis cantik ini lahir pada tanggal 15 Desember 1998, yaps, Natasha Wilona lahir pada tahun 1998, banyak yang tidak mengira kalau Natasha Wilona masih semuda ini, mungkin karena wajah Natasha Wilona yang sangat cantik,\n" +
////                "Natasha Wilona ini mulai dikenal banyak orang.\n");
////        itemObjectArrayList.add(movie1);
////
////        item_object movie2 = new item_object();
////        movie2.setNama("rina");
////        movie2.setGambar("https://cdns.klimg.com/resized/670x335/p/headline/rina-nose-lepas-hijab-begini-gambaran-s-61a3a3.jpg");
////        movie2.setDeskripsi("Nurina Permata Putri (lahir di Bandung, 16 Januari 1984; umur 34 tahun) adalah seorang aktris, pembawa acara, penyanyi, dan pelawak berkebangsaan Indonesia. Ia terlahir dengan kembarannya yang bernama Krisna.\n" +
////                "Setelah pernikahan pertamanya kandas, pada tanggal 12 Desember 2012 Rina menikah dengan Ridwan Feberani Anwar di Bali .\n");
////        itemObjectArrayList.add(movie2);
////
////        item_object movie3 = new item_object();
////        movie3.setNama("iis");
////        movie3.setGambar("http://cdn2.tstatic.net/style/foto/bank/images/iis-dahlia_20170121_075032.jpg");
////        movie3.setDeskripsi("Iis Laeliyah (lahir di Bongas, Indramayu, 29 Mei 1972; umur 46 tahun) adalah penyanyi dan juga aktris berkebangsaan Indonesia. Album-album Iis sukses dan melegenda ditelinga masyarakat.");
////        itemObjectArrayList.add(movie3);
////
////        item_object movie4 = new item_object();
////        movie4.setNama("devano");
////        movie4.setGambar("https://alibaba.kumpar.com/kumpar/image/upload/c_fill,g_face,f_jpg,q_auto,fl_progressive,fl_lossy,w_800/tyjmfu209gbenra24pax.jpg");
////        movie4.setDeskripsi("Devano Danendra (lahir 23 September 2002; umur 16 tahun) adalah penyanyi asal Indonesia. Ia merupakan Putra Kandung dari penyanyi dangdut Iis Dahlia.\n");
////        itemObjectArrayList.add(movie4);
//
//   // }
//
//}

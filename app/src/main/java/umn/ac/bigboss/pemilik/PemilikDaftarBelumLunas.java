package umn.ac.bigboss.pemilik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.adapter.AdapterDataBelumBayarBulanan;
import umn.ac.bigboss.pemilik.adapter.AdapterDataBelumLunas;
import umn.ac.bigboss.pengontrak.PengontrakEditProfile;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;

public class PemilikDaftarBelumLunas extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataBelumLunas adapterData;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    List<RequestPembayaranPengontrakmodel> listData;
    String TokenSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_daftar_belum_lunas);
//        my_toolbar = findViewById(R.id.my_toolbar_belum_lunas_pemilik);
//        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
//        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
//
//
//        my_toolbar_title.setText("Belum Lunas");
//        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
//        my_toolbar_title.setTextSize(16);
//        setSupportActionBar(my_toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        TokenSP = sh.getString("token", "0");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        my_toolbar = findViewById(R.id.my_toolbar_belum_lunas_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Belum Lunas");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, my_toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.hitam));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sidebar_globe:
                        Intent intent4 = new Intent(PemilikDaftarBelumLunas.this, PemilikHomeActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.sidebar_tambah_pembayaran:
                        Intent intent = new Intent(PemilikDaftarBelumLunas.this, PemilikTambahPembayaran.class);
                        startActivity(intent);
                        break;
                    case R.id.sidebar_daftar_belum_lunas:
//                        Fragment fragment = new PemilikBelumBayarBulanan();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.container_pemilik, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                        Intent intent1 = new Intent(PemilikDaftarBelumLunas.this, PemilikDaftarBelumLunas.class);
                        startActivity(intent1);
                        break;
                    case R.id.sidebar_daftar_orang_ngontrak:
                        Intent intent2 = new Intent(PemilikDaftarBelumLunas.this, PemilikDaftarOrangNgontrak.class);
                        startActivity(intent2);
                        break;
                    case R.id.sidebar_request_pembayaran:
                        Intent intent3 = new Intent(PemilikDaftarBelumLunas.this, PemilikRequestPembayaran.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        //        RECYCLE VIEW

        recyclerView = findViewById(R.id.rv_pemilik_belum_lunas);
        linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();

//        for(int i = 1; i < 10; i++){
//            listData.add("Bulan Ke - " + i);
//        }



//        adapterData = new AdapterDataBelumLunas(PemilikDaftarBelumLunas.this, listData);
//        recyclerView.setAdapter(adapterData);
//        adapterData.notifyDataSetChanged();

//        AKHIR RECYCLE VIEW


    }

    private void getData() {
        String token = "Bearer "+TokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<DataRequestPembayaranPengontrakModel> simpanData = api.ARGetPembayaranBelumLunas(token);
        simpanData.enqueue(new Callback<DataRequestPembayaranPengontrakModel>() {
            @Override
            public void onResponse(Call<DataRequestPembayaranPengontrakModel> call, Response<DataRequestPembayaranPengontrakModel> response) {
                if(response.isSuccessful()){
                    listData = response.body().getData();
                    adapterData = new AdapterDataBelumLunas(PemilikDaftarBelumLunas.this, listData);
                    recyclerView.setAdapter(adapterData);
                    adapterData.notifyDataSetChanged();
                }else{
                    Toast.makeText(PemilikDaftarBelumLunas.this, "gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataRequestPembayaranPengontrakModel> call, Throwable t) {
                Toast.makeText(PemilikDaftarBelumLunas.this, "gagal menghubungi server", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
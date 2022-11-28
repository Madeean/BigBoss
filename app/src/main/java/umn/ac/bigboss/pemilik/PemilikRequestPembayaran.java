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
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.adapter.AdapterDataBelumBayarBulanan;
import umn.ac.bigboss.pemilik.adapter.AdapterDataRequestPembayaran;
import umn.ac.bigboss.pengontrak.PengontrakEditProfile;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;

public class PemilikRequestPembayaran extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataRequestPembayaran adapterData;
    List<RequestPembayaranPengontrakmodel> listData;

    Toolbar my_toolbar;
    TextView my_toolbar_title;

    String tokenSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_request_pembayaran);

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        tokenSP = sh.getString("token", "");
//        my_toolbar = findViewById(R.id.my_toolbar_request_pembayaran_pemilik);
//        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
//        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
//
//
//        my_toolbar_title.setText("Request Pembayaran");
//        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
//        my_toolbar_title.setTextSize(16);
//        setSupportActionBar(my_toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));


        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        my_toolbar = findViewById(R.id.my_toolbar_request_pembayaran_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Request Pembayaran");
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
                        Intent intent4 = new Intent(PemilikRequestPembayaran.this, PemilikHomeActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.sidebar_tambah_pembayaran:
                        Intent intent = new Intent(PemilikRequestPembayaran.this, PemilikTambahPembayaran.class);
                        startActivity(intent);
                        break;
                    case R.id.sidebar_daftar_belum_lunas:
//                        Fragment fragment = new PemilikBelumBayarBulanan();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.container_pemilik, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                        Intent intent1 = new Intent(PemilikRequestPembayaran.this, PemilikDaftarBelumLunas.class);
                        startActivity(intent1);
                        break;
                    case R.id.sidebar_daftar_orang_ngontrak:
                        Intent intent2 = new Intent(PemilikRequestPembayaran.this, PemilikDaftarOrangNgontrak.class);
                        startActivity(intent2);
                        break;
                    case R.id.sidebar_request_pembayaran:
                        Intent intent3 = new Intent(PemilikRequestPembayaran.this, PemilikRequestPembayaran.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });


//        RECYCLE VIEW

        recyclerView = findViewById(R.id.rv_pemilik_request_pembayaran);
        linearLayoutManager = new LinearLayoutManager(PemilikRequestPembayaran.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        ArrayList listData = new ArrayList<>();
//
//        for(int i = 1; i < 10; i++){
//            listData.add("Made - " + i);
//        }
//
//        li
//
//        adapterData = new AdapterDataRequestPembayaran(this, listData);
//        recyclerView.setAdapter(adapterData);
//        adapterData.notifyDataSetChanged();

//        AKHIR RECYCLE VIEW

        getData();
    }

    public void getData(){
        String token  = "Bearer " + this.tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<DataRequestPembayaranPengontrakModel> simpanData = api.ARRequestPembayaranPemilik(token);
        simpanData.enqueue(new Callback<DataRequestPembayaranPengontrakModel>() {
            @Override
            public void onResponse(Call<DataRequestPembayaranPengontrakModel> call, Response<DataRequestPembayaranPengontrakModel> response) {
                if(response.isSuccessful()){
                    listData = response.body().getData();
                    adapterData = new AdapterDataRequestPembayaran(PemilikRequestPembayaran.this, listData);
                    recyclerView.setAdapter(adapterData);
                    adapterData.notifyDataSetChanged();

                }else{
                    Toast.makeText(PemilikRequestPembayaran.this, "gagal mendapatkan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataRequestPembayaranPengontrakModel> call, Throwable t) {
                Toast.makeText(PemilikRequestPembayaran.this, "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
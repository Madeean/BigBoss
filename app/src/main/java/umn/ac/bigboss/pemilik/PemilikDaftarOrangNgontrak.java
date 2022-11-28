package umn.ac.bigboss.pemilik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.GetUserKontrakanModel;
import umn.ac.bigboss.pemilik.adapter.AdapterDataDaftarOrangNgontrak;

public class PemilikDaftarOrangNgontrak extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterDataDaftarOrangNgontrak adapterDataDaftarOrangNgontrak;

    String tokenSP;

    List<DataLoginModel> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_daftar_orang_ngontrak);

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        tokenSP = sh.getString("token", "");




        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        my_toolbar = findViewById(R.id.my_toolbar_daftar_orang_ngontrak_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Daftar Orang Ngontrak");
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
                        Intent intent4 = new Intent(PemilikDaftarOrangNgontrak.this, PemilikHomeActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.sidebar_tambah_pembayaran:
                        Intent intent = new Intent(PemilikDaftarOrangNgontrak.this, PemilikTambahPembayaran.class);
                        startActivity(intent);
                        break;
                    case R.id.sidebar_daftar_belum_lunas:
//                        Fragment fragment = new PemilikBelumBayarBulanan();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.container_pemilik, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                        Intent intent1 = new Intent(PemilikDaftarOrangNgontrak.this, PemilikDaftarBelumLunas.class);
                        startActivity(intent1);
                        break;
                    case R.id.sidebar_daftar_orang_ngontrak:
                        Intent intent2 = new Intent(PemilikDaftarOrangNgontrak.this, PemilikDaftarOrangNgontrak.class);
                        startActivity(intent2);
                        break;
                    case R.id.sidebar_request_pembayaran:
                        Intent intent3 = new Intent(PemilikDaftarOrangNgontrak.this, PemilikRequestPembayaran.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });


//        RecycleView

        recyclerView = findViewById(R.id.rv_daftar_orang_ngontrak_pemilik);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        getData();
//        int []arr = {R.drawable.kucing_topi};
//        adapterDataDaftarOrangNgontrak = new AdapterDataDaftarOrangNgontrak(arr);
//        recyclerView.setAdapter(adapterDataDaftarOrangNgontrak);
//        recyclerView.setHasFixedSize(true);

//        Akhir recyclew View



    }

    private void getData() {
        String token = "Bearer "+ this.tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<GetUserKontrakanModel> simpanData = api.ARDaftarPengontrak(token);
        simpanData.enqueue(new Callback<GetUserKontrakanModel>() {
            @Override
            public void onResponse(Call<GetUserKontrakanModel> call, Response<GetUserKontrakanModel> response) {
                if (response.isSuccessful()){

                    listData = response.body().getUser();
//                    Toast.makeText(PemilikDaftarOrangNgontrak.this, listData.toString(), Toast.LENGTH_SHORT).show();
//
                    adapterDataDaftarOrangNgontrak = new AdapterDataDaftarOrangNgontrak(listData);
                    recyclerView.setAdapter(adapterDataDaftarOrangNgontrak);
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<GetUserKontrakanModel> call, Throwable t) {
                Toast.makeText(PemilikDaftarOrangNgontrak.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
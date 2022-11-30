package umn.ac.bigboss.pemilik;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.PembayaranModel;


public class PemilikSetting extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar my_toolbar;

    TextView my_toolbar_title,name_setting_pemilik,email_setting_pemilik;

    Button btn_logout_setting_pemilik, btn_edit_profile_setting_pemilik;

    String tokenSP;

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pemilik_setting, container, false);

        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        String name = sh.getString("name", "");
        String email = sh.getString("email", "");
        String nama_kontrakan = sh.getString("nama_kontrakan", "");
        int umur = sh.getInt("umur", 0);
        tokenSP = sh.getString("token", "");

        name_setting_pemilik = view.findViewById(R.id.name_setting_pemilik);
        email_setting_pemilik = view.findViewById(R.id.email_setting_pemilik);

        name_setting_pemilik.setText(name);
        email_setting_pemilik.setText(email);

//        my_toolbar = view.findViewById(R.id.my_toolbar_setting_pemilik);
//        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
//        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
//
//
//        my_toolbar_title.setText("Setting");
//        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
//        my_toolbar_title.setTextSize(16);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(my_toolbar);
//
//
//        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));
//
        btn_edit_profile_setting_pemilik = view.findViewById(R.id.btn_edit_profile_setting_pemilik);
        btn_edit_profile_setting_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PemilikEditProfile.class);
                startActivity(intent);
            }
        });

        btn_logout_setting_pemilik = view.findViewById(R.id.btn_logout_setting_pemilik);
        btn_logout_setting_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
//
//        drawerLayout = view.findViewById(R.id.drawer_layout);
//        navigationView = view.findViewById(R.id.nav_view);
//        drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.globe_pemilik:
//                        Intent intent = new Intent(getActivity(), PemilikHome.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.search_pemilik:
//                        Intent intent1 = new Intent(getActivity(), PemilikBelumBayarBulanan.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.setting_pemilik:
//                        Intent intent2 = new Intent(getActivity(), PemilikSetting.class);
//                        startActivity(intent2);
//                        break;
//                }
//                return false;
//            }
//        });

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        my_toolbar = view.findViewById(R.id.my_toolbar_setting_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Setting");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(my_toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawerLayout, my_toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.hitam));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sidebar_globe:
                        Intent intent4 = new Intent(getActivity(), PemilikHomeActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.sidebar_tambah_pembayaran:
                        Intent intent = new Intent(getActivity(), PemilikTambahPembayaran.class);
                        startActivity(intent);
                        break;
                    case R.id.sidebar_daftar_belum_lunas:
//                        Fragment fragment = new PemilikBelumBayarBulanan();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.container_pemilik, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                        Intent intent1 = new Intent(getActivity(), PemilikDaftarBelumLunas.class);
                        startActivity(intent1);
                        break;
                    case R.id.sidebar_daftar_orang_ngontrak:
                        Intent intent2 = new Intent(getActivity(), PemilikDaftarOrangNgontrak.class);
                        startActivity(intent2);
                        break;
                    case R.id.sidebar_request_pembayaran:
                        Intent intent3 = new Intent(getActivity(), PemilikRequestPembayaran.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });




        return view;
    }

    public void logout(){
        String token = "Bearer "+tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<PembayaranModel> simpanData = api.ARLogout(token);
        simpanData.enqueue(new Callback<PembayaranModel>() {
            @Override
            public void onResponse(Call<PembayaranModel> call, Response<PembayaranModel> response) {
                if(response.isSuccessful()){
                    String pesan = response.body().getMessage();
                    Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Gagal Logout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PembayaranModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
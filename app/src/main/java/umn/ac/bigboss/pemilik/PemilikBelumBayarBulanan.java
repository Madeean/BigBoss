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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.DataYangSudahBayarModel;
import umn.ac.bigboss.modelauth.PemilikBelumBayarBulananModel;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.adapter.AdapterDataBelumBayarBulanan;
import umn.ac.bigboss.pemilik.adapter.AdapterDataHistoryPembayaranPemilik;
import umn.ac.bigboss.pengontrak.PengontrakDetailPembayaran;
import umn.ac.bigboss.pengontrak.PengontrakHome;
import umn.ac.bigboss.pengontrak.adapter.AdapterDataRequestPembayaranPengontrak;


public class PemilikBelumBayarBulanan extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataBelumBayarBulanan adapterData;
    List<DataLoginModel> listData;


    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar my_toolbar;
    TextView my_toolbar_title;

    ArrayList<String> list = new ArrayList<String>();
    int pilih_bulan;


    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public String tokenSP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pemilik_belum_bayar_bulanan, container, false);

        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        tokenSP = sh.getString("token", "");

        list.add("Pilih Bulan");
        list.add("Januari");
        list.add("Februari");
        list.add("Maret");
        list.add("April");
        list.add("Mei");
        list.add("Juni");
        list.add("Juli");
        list.add("Agustus");
        list.add("September");
        list.add("Oktober");
        list.add("November");
        list.add("Desember");

        Spinner s = (Spinner) view.findViewById(R.id.spinner_pilih_bulan_belum_bayar_bulanan_pemilik);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(position >0 ){
                    pilih_bulan = position;
                    getData(pilih_bulan);

                }else{

                    Toast.makeText(getActivity(), "pilih bulan dahulu", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM");
        date = dateFormat.format(calendar.getTime());


        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        my_toolbar = view.findViewById(R.id.my_toolbar_belum_bayar_bulanan_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Belum Bayar Bulanan");
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



//        RECYCLE VIEW

        recyclerView = view.findViewById(R.id.rv_pemilik_belum_bayar_bulanan);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        listData = new ArrayList<>();
//
//        for(int i = 1; i < 10; i++){
//            listData.add("Bulan Ke - " + i);
//        }
//

//
//        adapterData = new AdapterDataBelumBayarBulanan(getActivity(), listData);
//        recyclerView.setAdapter(adapterData);
//        adapterData.notifyDataSetChanged();

//        AKHIR RECYCLE VIEW




        return view;
    }

    private void getData(int pilih_bulan) {
        String token = "Bearer "+tokenSP;
        ApiRequest api  = Server.konekRetrofit().create(ApiRequest.class);
        Call<PemilikBelumBayarBulananModel> tampilData = api.ARBelumBayarBulanan(pilih_bulan, token);
        tampilData.enqueue(new Callback<PemilikBelumBayarBulananModel>() {
            @Override
            public void onResponse(Call<PemilikBelumBayarBulananModel> call, Response<PemilikBelumBayarBulananModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage() != null){
                        listData = response.body().getData();
                        adapterData = new AdapterDataBelumBayarBulanan(getActivity(), listData,pilih_bulan);
                        recyclerView.setAdapter(adapterData);
                        adapterData.notifyDataSetChanged();
                    }else{
                        listData = response.body().getData_yang_belum_bayar();
                        adapterData = new AdapterDataBelumBayarBulanan(getActivity(), listData,pilih_bulan);
                        recyclerView.setAdapter(adapterData);
                        adapterData.notifyDataSetChanged();
                        System.out.println("RESPONSE : "+response.body().getData_yang_belum_bayar());
                    }



                }else{
                    Toast.makeText(getActivity(), "gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PemilikBelumBayarBulananModel> call, Throwable t) {
                System.out.println("error : "+t.getMessage());
                Toast.makeText(getActivity(), "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.GetJumlahOrangNgontrakModel;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.adapter.AdapterDataHistoryPembayaranPemilik;
import umn.ac.bigboss.pengontrak.adapter.adapter_data_history_pembayaran_pengontrak;


public class PemilikHome extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataHistoryPembayaranPemilik adapterData;
    List<RequestPembayaranPengontrakmodel> listData;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar my_toolbar;
    TextView start_toolbar, start_toolbar_tanggal;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date,nameSP,tokenSP;

    int roomsSP;

    EditText search_input;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pemilik_home, container, false);

        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        nameSP = sh.getString("name", "");
        String nama_kontrakan = sh.getString("nama_kontrakan", "");
        tokenSP = sh.getString("token", "");
        roomsSP = sh.getInt("rooms", 0);

        search_input = view.findViewById(R.id.search_input);
        search_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    search_input.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_search_24, 0, 0, 0);
                }
                else
                {
                    search_input.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText( getActivity(), s.toString(), Toast.LENGTH_SHORT).show();
            }
        });






        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        my_toolbar = view.findViewById(R.id.my_toolbar_home_pemilik);
        start_toolbar = my_toolbar.findViewById(R.id.start_toolbar_text);
        start_toolbar_tanggal = my_toolbar.findViewById(R.id.start_toolbar_text_tanggal);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        start_toolbar.setText(nameSP+"\nJumlah Orang 2/30");
        start_toolbar.setTextColor(getResources().getColor(R.color.hitam));
        start_toolbar.setTextSize(16);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());
        start_toolbar_tanggal.setText(date);
        start_toolbar_tanggal.setTextColor(getResources().getColor(R.color.hitam));
        start_toolbar_tanggal.setTextSize(16);



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

        getNameAndJumlahOrangNgontrak();



//        RECYCLE VIEW

        recyclerView = view.findViewById(R.id.rv_history_pembayaran_pemilik);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();

//
//        adapterData = new AdapterDataHistoryPembayaranPemilik(getActivity(), listData);
//        recyclerView.setAdapter(adapterData);
//        adapterData.notifyDataSetChanged();

//        AKHIR RECYCLE VIEW



        return view;
    }

    private void getData() {
        String token = "Bearer "+tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<DataRequestPembayaranPengontrakModel> simpanData = api.ARHistoryPembayaranPemilik(token);
        simpanData.enqueue(new Callback<DataRequestPembayaranPengontrakModel>() {
            @Override
            public void onResponse(Call<DataRequestPembayaranPengontrakModel> call, Response<DataRequestPembayaranPengontrakModel> response) {
                if(response.isSuccessful()) {
                    listData = response.body().getData();
                    adapterData = new AdapterDataHistoryPembayaranPemilik(getActivity(), listData);
                    recyclerView.setAdapter(adapterData);
                    adapterData.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataRequestPembayaranPengontrakModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal menghubungkan server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNameAndJumlahOrangNgontrak() {
        String token = "Bearer "+tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<GetJumlahOrangNgontrakModel> simpanData = api.ARJumlahOrangNgontrak(token);
        simpanData.enqueue(new Callback<GetJumlahOrangNgontrakModel>() {
            @Override
            public void onResponse(Call<GetJumlahOrangNgontrakModel> call, Response<GetJumlahOrangNgontrakModel> response) {
                if(response.isSuccessful()){
                    int jumlahOrang = response.body().getData();
                    start_toolbar.setText(nameSP+"\nJumlah Orang "+jumlahOrang+"/"+roomsSP);
                }else{
                    Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetJumlahOrangNgontrakModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal menghubungkan server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
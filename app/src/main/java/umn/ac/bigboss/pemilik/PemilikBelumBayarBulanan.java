package umn.ac.bigboss.pemilik;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.pemilik.adapter.AdapterDataBelumBayarBulanan;
import umn.ac.bigboss.pemilik.adapter.AdapterDataHistoryPembayaranPemilik;
import umn.ac.bigboss.pengontrak.PengontrakDetailPembayaran;
import umn.ac.bigboss.pengontrak.PengontrakHome;


public class PemilikBelumBayarBulanan extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataBelumBayarBulanan adapterData;
    List<String> listData;

    Toolbar my_toolbar;
    TextView my_toolbar_title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pemilik_belum_bayar_bulanan, container, false);

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

        recyclerView = view.findViewById(R.id.rv_pemilik_belum_bayar_bulanan);
        listData = new ArrayList<>();

        for(int i = 1; i < 10; i++){
            listData.add("Bulan Ke - " + i);
        }

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterData = new AdapterDataBelumBayarBulanan(getActivity(), listData);
        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

        return view;
    }
}
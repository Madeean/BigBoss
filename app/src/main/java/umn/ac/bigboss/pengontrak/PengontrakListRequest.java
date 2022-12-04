package umn.ac.bigboss.pengontrak;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pengontrak.adapter.AdapterDataRequestPembayaranPengontrak;
import umn.ac.bigboss.pengontrak.adapter.adapter_data_history_pembayaran_pengontrak;


public class PengontrakListRequest extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataRequestPembayaranPengontrak adapterData;
    List<RequestPembayaranPengontrakmodel> listData;

    Toolbar my_toolbar;
    TextView my_toolbar_title;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public String tokenSP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengontrak_list_request, container, false);

        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        tokenSP = sh.getString("token", "");

        my_toolbar = view.findViewById(R.id.my_toolbar_list_requerst_pengontrak);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));



        my_toolbar_title.setText("Request Pembayaran");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(my_toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);


        recyclerView = view.findViewById(R.id.rv_request_pembayaran_pengontrak);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
//        listData = new ArrayList<>();
//
//
//
//
//
//        adapterData = new AdapterDataRequestPembayaranPengontrak(getActivity(), listData);
//        recyclerView.setAdapter(adapterData);
//        adapterData.notifyDataSetChanged();

        return view;
    }

    public void getData(){
        ApiRequest api  = Server.konekRetrofit().create(ApiRequest.class);
        Call<DataRequestPembayaranPengontrakModel> tampilData = api.ARListRequest("Bearer " + tokenSP);
        tampilData.enqueue(new Callback<DataRequestPembayaranPengontrakModel>() {
            @Override
            public void onResponse(Call<DataRequestPembayaranPengontrakModel> call, Response<DataRequestPembayaranPengontrakModel> response) {
                if (response.isSuccessful()){
                    listData = response.body().getData();
                    adapterData = new AdapterDataRequestPembayaranPengontrak(getActivity(), listData);
                    recyclerView.setAdapter(adapterData);
                    adapterData.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataRequestPembayaranPengontrakModel> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
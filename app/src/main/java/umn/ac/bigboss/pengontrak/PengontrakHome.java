package umn.ac.bigboss.pengontrak;

import static android.content.Context.MODE_APPEND;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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


public class PengontrakHome extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_data_history_pembayaran_pengontrak adapterData;
    List<RequestPembayaranPengontrakmodel> listData;

    Toolbar start_toolbar_pengontrak_home;
    TextView start_toolbar, start_toolbar_tanggal;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    private String token;

    EditText search_input;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pengontrak_home, container, false);

        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
         token = sh.getString("token", "");



        search_input = view.findViewById(R.id.search_input);

        start_toolbar_pengontrak_home = view.findViewById(R.id.start_toolbar_pengontrak_home);
        start_toolbar = start_toolbar_pengontrak_home.findViewById(R.id.start_toolbar_text);
        start_toolbar = start_toolbar_pengontrak_home.findViewById(R.id.start_toolbar_text);
        start_toolbar_tanggal = start_toolbar_pengontrak_home.findViewById(R.id.start_toolbar_text_tanggal);
        start_toolbar_pengontrak_home.setBackgroundColor(getResources().getColor(R.color.abuabumuda));


        start_toolbar.setText("Hi, Madee");
        start_toolbar.setTextColor(getResources().getColor(R.color.hitam));
        start_toolbar.setTextSize(16);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());


        start_toolbar_tanggal.setText(date);
        start_toolbar_tanggal.setTextColor(getResources().getColor(R.color.hitam));
        start_toolbar_tanggal.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(start_toolbar_pengontrak_home);

        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);


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

        recyclerView = view.findViewById(R.id.rv_history_pembayaran_pengontrak);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
//
//        adapterData = new adapter_data_history_pembayaran_pengontrak(getActivity(), listData);
//        recyclerView.setAdapter(adapterData);
//        adapterData.notifyDataSetChanged();

        return view;
    }

    public void getData(){
        ApiRequest api  = Server.konekRetrofit().create(ApiRequest.class);
        Call<DataRequestPembayaranPengontrakModel> tampilData = api.ARHistoryPembayaran("Bearer " + token);
        tampilData.enqueue(new Callback<DataRequestPembayaranPengontrakModel>() {
            @Override
            public void onResponse(Call<DataRequestPembayaranPengontrakModel> call, Response<DataRequestPembayaranPengontrakModel> response) {
                if (response.isSuccessful()){
                    listData = response.body().getData();
                    System.out.println("list data: " + listData);
                    adapterData = new adapter_data_history_pembayaran_pengontrak(getActivity(), listData);
                    recyclerView.setAdapter(adapterData);
                    adapterData.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataRequestPembayaranPengontrakModel> call, Throwable t) {
                Toast.makeText(getActivity(), "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("error" + t.getMessage());
            }
        });
    }


}
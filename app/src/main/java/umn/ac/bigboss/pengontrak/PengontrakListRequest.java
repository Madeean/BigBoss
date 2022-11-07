package umn.ac.bigboss.pengontrak;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.pengontrak.adapter.AdapterDataRequestPembayaranPengontrak;
import umn.ac.bigboss.pengontrak.adapter.adapter_data_history_pembayaran_pengontrak;


public class PengontrakListRequest extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataRequestPembayaranPengontrak adapterData;
    List<String> listData;

    Toolbar my_toolbar;
    TextView my_toolbar_title;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengontrak_list_request, container, false);

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
        listData = new ArrayList<>();

        for(int i = 1; i < 10; i++){
            listData.add("madee - " + i);
        }

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterData = new AdapterDataRequestPembayaranPengontrak(getActivity(), listData);
        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

        return view;
    }
}
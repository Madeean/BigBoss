package umn.ac.bigboss.pemilik;

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

import umn.ac.bigboss.R;
import umn.ac.bigboss.pemilik.adapter.AdapterDataHistoryPembayaranPemilik;
import umn.ac.bigboss.pengontrak.adapter.adapter_data_history_pembayaran_pengontrak;


public class PemilikHome extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterDataHistoryPembayaranPemilik adapterData;
    List<String> listData;

    Toolbar start_toolbar_pemilik_home;
    TextView start_toolbar, start_toolbar_tanggal;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    EditText search_input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pemilik_home, container, false);

        search_input = view.findViewById(R.id.search_input);

        start_toolbar_pemilik_home = view.findViewById(R.id.my_toolbar_home_pemilik);
        start_toolbar = start_toolbar_pemilik_home.findViewById(R.id.start_toolbar_text);
        start_toolbar_tanggal = start_toolbar_pemilik_home.findViewById(R.id.start_toolbar_text_tanggal);
        start_toolbar_pemilik_home.setBackgroundColor(getResources().getColor(R.color.abuabumuda));


        start_toolbar.setText("Madee\nJumlah Orang 2/30");
        start_toolbar.setTextColor(getResources().getColor(R.color.hitam));
        start_toolbar.setTextSize(16);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());


        start_toolbar_tanggal.setText(date);
        start_toolbar_tanggal.setTextColor(getResources().getColor(R.color.hitam));
        start_toolbar_tanggal.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(start_toolbar_pemilik_home);

        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        start_toolbar_pemilik_home.setNavigationIcon(getResources().getDrawable(R.drawable.list));


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

        recyclerView = view.findViewById(R.id.rv_history_pembayaran_pemilik);
        listData = new ArrayList<>();

        for(int i = 1; i < 10; i++){
            listData.add("madee - " + i);
        }

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterData = new AdapterDataHistoryPembayaranPemilik(getActivity(), listData);
        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();



        return view;
    }
}
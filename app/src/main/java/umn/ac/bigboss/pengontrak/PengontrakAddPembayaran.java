package umn.ac.bigboss.pengontrak;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.RegisterActivity;

public class PengontrakAddPembayaran extends Fragment {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_pengontrak_add_pembayaran, container, false);

        my_toolbar = view.findViewById(R.id.my_toolbar_add_pembayaran_pengontrak);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM");
        date = dateFormat.format(calendar.getTime());

        my_toolbar_title.setText("Pembayaran Bulan "+date);
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(my_toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);


       return view;
    }
}
package umn.ac.bigboss.pemilik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import umn.ac.bigboss.R;

public class PemilikTambahPembayaran extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    Toolbar my_toolbar;
    TextView my_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_tambah_pembayaran);

        my_toolbar = findViewById(R.id.my_toolbar_belum_bayar_bulanan_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());

        my_toolbar_title.setText("Pembayaran bulan "+date);
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        ArrayList<String> list = new ArrayList<String>();   //make this as field atribute
        list.add("Pilih nama Pengontrak");
        list.add("Kevin");
        list.add("Kevin 2");
        Spinner s = (Spinner) findViewById(R.id.spinner_pemilik);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                list.get(position);
                if(position >0 ){
                    Toast.makeText(PemilikTambahPembayaran.this, list.get(position), Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(PemilikTambahPembayaran.this, "Please select", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
}
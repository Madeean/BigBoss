package umn.ac.bigboss.pengontrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.RegisterActivity;

public class PengontrakHomeActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    BottomNavigationView bottomNavigationView ;
    PengontrakHome pengontrakHome = new PengontrakHome();
    PengontrakListRequest pengontrakListRequest = new PengontrakListRequest();
    PengontrakAddPembayaran pengontrakAddPembayaran =  new PengontrakAddPembayaran();
    PengontrakSetting pengontrakSetting = new PengontrakSetting();

    public String email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengontrak_home);
//        get intent
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");

        bottomNavigationView = findViewById(R.id.bottom_nav_pengontrak);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_pengontrak, pengontrakHome).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.globe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pengontrak, pengontrakHome).commit();
                        return true;
                    case R.id.add:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pengontrak, pengontrakAddPembayaran).commit();
                        return true;
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pengontrak, pengontrakListRequest).commit();
                        return true;
                    case R.id.setting:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pengontrak, pengontrakSetting).commit();
//                        send name and email to fragment
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("email", email);
                        pengontrakSetting.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pengontrak, pengontrakSetting).commit();
                        return true;
                }

                return false;
            }

        });

//        search_input = findViewById(R.id.search_input);
//
//        start_toolbar_pengontrak_home = findViewById(R.id.start_toolbar_pengontrak_home);
//        start_toolbar = start_toolbar_pengontrak_home.findViewById(R.id.start_toolbar_text);
//        start_toolbar = start_toolbar_pengontrak_home.findViewById(R.id.start_toolbar_text);
//        start_toolbar_tanggal = start_toolbar_pengontrak_home.findViewById(R.id.start_toolbar_text_tanggal);
//        start_toolbar_pengontrak_home.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
//
//
//        start_toolbar.setText("Hi, Madee");
//        start_toolbar.setTextColor(getResources().getColor(R.color.hitam));
//        start_toolbar.setTextSize(16);
//
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        date = dateFormat.format(calendar.getTime());
//
//
//        start_toolbar_tanggal.setText(date);
//        start_toolbar_tanggal.setTextColor(getResources().getColor(R.color.hitam));
//        start_toolbar_tanggal.setTextSize(16);
//
//        setSupportActionBar(start_toolbar_pengontrak_home);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//
//        search_input.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.length() == 0) {
//                    search_input.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_search_24, 0, 0, 0);
//                }
//                else
//                {
//                    search_input.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Toast.makeText(PengontrakHomeActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            moveTaskToBack(true);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "tekan kembali dua kali, jika ingin menutup aplikasi", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
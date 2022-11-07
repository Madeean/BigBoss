package umn.ac.bigboss.pemilik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import umn.ac.bigboss.R;
import umn.ac.bigboss.pengontrak.PengontrakAddPembayaran;
import umn.ac.bigboss.pengontrak.PengontrakHome;
import umn.ac.bigboss.pengontrak.PengontrakListRequest;
import umn.ac.bigboss.pengontrak.PengontrakSetting;

public class PemilikHomeActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;

    BottomNavigationView bottomNavigationView ;
    PemilikHome pemilikHome = new PemilikHome();
    PemilikBelumBayarBulanan pemilikBelumBayarBulanan = new PemilikBelumBayarBulanan();
    PemilikSetting pemilikSetting = new PemilikSetting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_home);

        bottomNavigationView = findViewById(R.id.bottom_nav_pemilik);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_pemilik, pemilikHome).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.globe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pemilik, pemilikHome).commit();
                        return true;
                    case R.id.belum_bayar_bulanan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pemilik, pemilikBelumBayarBulanan).commit();
                        return true;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_pemilik, pemilikSetting).commit();
                        return true;
                }

                return false;
            }
        });
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
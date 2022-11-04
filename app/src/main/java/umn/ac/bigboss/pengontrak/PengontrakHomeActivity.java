package umn.ac.bigboss.pengontrak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import umn.ac.bigboss.R;

public class PengontrakHomeActivity extends AppCompatActivity {

    Toolbar my_toolbar;
    TextView my_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengontrak_home);



    }
}
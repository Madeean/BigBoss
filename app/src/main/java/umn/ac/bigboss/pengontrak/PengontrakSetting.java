package umn.ac.bigboss.pengontrak;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;

public class PengontrakSetting extends Fragment {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    Button btn_logout,btn_EditProfile;

    TextView name_setting_pengontrak,email_setting_pengontrak;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengontrak_setting, container, false);
//        get data intent from pengontrak home activity
        String name = getArguments().getString("name");
        String email = getArguments().getString("email");

        name_setting_pengontrak = view.findViewById(R.id.name_setting_pengontrak);
        email_setting_pengontrak = view.findViewById(R.id.email_setting_pengontrak);
        name_setting_pengontrak.setText(name);
        email_setting_pengontrak.setText(email);

        my_toolbar = view.findViewById(R.id.my_toolbar_list_requerst_pengontrak);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));



        my_toolbar_title.setText("Setting");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(my_toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn_logout = view.findViewById(R.id.btn_logout_setting_pengontrak);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_EditProfile = view.findViewById(R.id.btn_edit_profile_setting_pengontrak);
        btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PengontrakEditProfile.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
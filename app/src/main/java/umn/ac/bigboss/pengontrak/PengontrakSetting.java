package umn.ac.bigboss.pengontrak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.PembayaranModel;

public class PengontrakSetting extends Fragment {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    Button btn_logout,btn_EditProfile;

    TextView name_setting_pengontrak,email_setting_pengontrak;
    String tokenSP;;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengontrak_setting, container, false);
//        get data intent from pengontrak home activity
        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        String nama_kontrakan = sh.getString("nama_kontrakan", "");
        int umur = sh.getInt("umur", 0);
        tokenSP = sh.getString("token", "");
        int id = sh.getInt("id", 0);

        name_setting_pengontrak = view.findViewById(R.id.name_setting_pengontrak);
        email_setting_pengontrak = view.findViewById(R.id.email_setting_pengontrak);

        getName(id);
//        name_setting_pengontrak.setText(name);
//        email_setting_pengontrak.setText(email);

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
                logout();
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

    private void getName(int id) {
        String token = "Bearer "+this.tokenSP;
        ApiRequest api  = Server.konekRetrofit().create(ApiRequest.class);
        Call<EditLogin> tampilData = api.ARDetailPengontrak(id,token);
        tampilData.enqueue(new Callback<EditLogin>() {
            @Override
            public void onResponse(Call<EditLogin> call, Response<EditLogin> response) {
                if (response.isSuccessful()){
                    String name = response.body().getUser().getName();
                    String email = response.body().getUser().getEmail();
                            name_setting_pengontrak.setText(name);
                            email_setting_pengontrak.setText(email);
                }else{
                    Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditLogin> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logout(){
        String token = "Bearer "+tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<PembayaranModel> simpanData = api.ARLogout(token);
        simpanData.enqueue(new Callback<PembayaranModel>() {
            @Override
            public void onResponse(Call<PembayaranModel> call, Response<PembayaranModel> response) {
                if(response.isSuccessful()){
                    String pesan = response.body().getMessage();
                    Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Gagal Logout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PembayaranModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
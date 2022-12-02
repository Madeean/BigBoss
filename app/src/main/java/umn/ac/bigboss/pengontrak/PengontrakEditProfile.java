package umn.ac.bigboss.pengontrak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.MainActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.RegisterActivity;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.LoginModel;

public class PengontrakEditProfile extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    EditText edit_profile_nama_pengontrak,edit_profile_email_pengontrak,edit_profile_umur_pengontrak;
    Button btn_edit_pengontrak;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengontrak_edit_profile);

        edit_profile_email_pengontrak = findViewById(R.id.edit_profile_email_pengontrak);
        edit_profile_nama_pengontrak = findViewById(R.id.edit_profile_nama_pengontrak);
        edit_profile_umur_pengontrak = findViewById(R.id.edit_profile_umur_pengontrak);
        btn_edit_pengontrak = findViewById(R.id.btn_edit_pengontrak);

        my_toolbar = findViewById(R.id.my_toolbar_edit_profile_pengontrak);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        token = sh.getString("token", "");
        String name = sh.getString("name", "");
        String email = sh.getString("email", "");
        int umur = sh.getInt("umur", 0);



        edit_profile_email_pengontrak.setText(email);
        edit_profile_nama_pengontrak.setText(name);
        edit_profile_umur_pengontrak.setText(String.valueOf(umur));


        my_toolbar_title.setText("Edit Profile");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_hitam));

        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengontrakEditProfile.this, PengontrakHomeActivity.class);
                startActivity(intent);
            }
        });

        btn_edit_pengontrak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_profile_nama_pengontrak.getText().toString().isEmpty() || edit_profile_email_pengontrak.getText().toString().isEmpty() || edit_profile_umur_pengontrak.getText().toString().isEmpty()){
                    Toast.makeText(PengontrakEditProfile.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    editProfile();
                }
            }
        });
    }

    private void editProfile() {
        String name = edit_profile_nama_pengontrak.getText().toString();
        String email = edit_profile_email_pengontrak.getText().toString();
        String umur = edit_profile_umur_pengontrak.getText().toString();
        String token = "Bearer "+this.token;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<EditLogin> simpanData = api.AREditProfilePengontrak(token,name,email,umur);
        simpanData.enqueue(new Callback<EditLogin>() {
            @Override
            public void onResponse(Call<EditLogin> call, Response<EditLogin> response) {
                if(response.isSuccessful()){
                    DataLoginModel data = response.body().getUser();
                    SharedPreferences sharedPreferences = getSharedPreferences("BigbossPreff",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("name",data.getName());
                    myEdit.putString("email",data.getEmail());
                    myEdit.putInt("umur",data.getUmur());
                    myEdit.apply();
                    Toast.makeText(PengontrakEditProfile.this, "Edit Profile Berhasil", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PengontrakEditProfile.this, PengontrakHomeActivity.class);
                    startActivity(intent);




                }
                else{
                    Toast.makeText(PengontrakEditProfile.this, "Edit Profile Gagal "+response.body(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EditLogin> call, Throwable t) {
                System.out.println("edit profile error"+t.getMessage());
                Toast.makeText(PengontrakEditProfile.this, "edit profile"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
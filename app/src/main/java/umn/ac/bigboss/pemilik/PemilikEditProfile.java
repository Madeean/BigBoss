package umn.ac.bigboss.pemilik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.pengontrak.PengontrakEditProfile;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;

public class PemilikEditProfile extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    EditText et_nama_pemilik,et_nama_kontrakan_pemilik,et_ruangan_yang_dipunya_pemilik;
    Button btn_editprofile_pemilik;

    String name,nama_kontrakan,token;
    int rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_edit_profile);

        et_nama_pemilik = findViewById(R.id.et_nama_pemilik);
        et_nama_kontrakan_pemilik = findViewById(R.id.et_nama_kontrakan_pemilik);
        et_ruangan_yang_dipunya_pemilik = findViewById(R.id.et_ruangan_yang_dipunya_pemilik);

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        String nameSP = sh.getString("name", "");
        int roomsSP = sh.getInt("rooms", 0);
        String nama_kontrakanSP = sh.getString("nama_kontrakan", "");
        token = sh.getString("token", "");

        et_nama_pemilik.setText(nameSP);
        et_nama_kontrakan_pemilik.setText(nama_kontrakanSP);
        et_ruangan_yang_dipunya_pemilik.setText(String.valueOf(roomsSP));





        my_toolbar = findViewById(R.id.my_toolbar_edit_profile_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));


        my_toolbar_title.setText("Edit Profile");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_hitam));

        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PemilikEditProfile.this, PemilikHomeActivity.class);
                startActivity(intent);
            }
        });

        btn_editprofile_pemilik = findViewById(R.id.btn_editprofile_pemilik);
        btn_editprofile_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editProfile();
            }
        });
    }

    public void editProfile(){
        name = et_nama_pemilik.getText().toString();
        nama_kontrakan = et_nama_kontrakan_pemilik.getText().toString();
        rooms = Integer.parseInt(et_ruangan_yang_dipunya_pemilik.getText().toString());
        String token = "Bearer "+this.token;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<EditLogin> simpanData = api.AREditProfilePemilik(token,name,nama_kontrakan,rooms);
        simpanData.enqueue(new Callback<EditLogin>() {
            @Override
            public void onResponse(Call<EditLogin> call, Response<EditLogin> response) {
                if(response.isSuccessful()){
                    DataLoginModel data = response.body().getUser();
                    Toast.makeText(PemilikEditProfile.this, "Edit Profile Berhasil", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("BigbossPreff",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("name",data.getName());
                    myEdit.putString("nama_kontrakan",data.getNama_kontrakan());
                    myEdit.putInt("rooms",data.getRooms());
                    myEdit.apply();

                    Intent intent = new Intent(PemilikEditProfile.this, PemilikHomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(PemilikEditProfile.this, "Edit Profile Gagal "+response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditLogin> call, Throwable t) {
                System.out.println("edit profile error"+t.getMessage());
                Toast.makeText(PemilikEditProfile.this, "edit profile"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
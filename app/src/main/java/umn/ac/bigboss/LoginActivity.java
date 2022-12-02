package umn.ac.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.pemilik.PemilikHomeActivity;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;

public class LoginActivity extends AppCompatActivity {

    public Button btn_register,btn_login;
    public EditText et_email_login, et_password_login;

    public String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email_login = findViewById(R.id.et_email_login);
        et_password_login = findViewById(R.id.et_password_login);

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                email = et_email_login.getText().toString();
                password = et_password_login.getText().toString();
                if (email.trim().equals("")) {
                    et_email_login.setError("Email tidak boleh kosong");
                }else if (password.trim().equals("")) {
                    et_password_login.setError("Password tidak boleh kosong");
                }else{
                    postData();
                }
            }
        });



    }

    public void postData(){
//        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
//        Call<LoginModel> simpanData = api.ARLogin(email,password);
//
//        simpanData.enqueue(new Callback<LoginModel>(){
//
//            @Override
//            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
//
//                String token = response.body().getToken();
//                DataLoginModel data = (DataLoginModel) response.body().getUser();
//
//                Toast.makeText(LoginActivity.this, data.getRole(), Toast.LENGTH_SHORT).show();
//                finish();
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "gagal menambah note : "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<LoginModel> simpanData = api.ARLogin(email,password);
        simpanData.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

//                check status code
                if(call.timeout().equals("")) {
                    Toast.makeText(LoginActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                }
                if(response.isSuccessful()){
                    System.out.println("on response masuk");
                    String token = response.body().getToken();
                    DataLoginModel data = response.body().getUser();
                    System.out.println("token : "+token);
                    System.out.println("user"+data.getRole());
                    String role = data.getRole();

                    SharedPreferences sharedPreferences = getSharedPreferences("BigbossPreff",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("token", token);
                    myEdit.putString("role", role);
                    myEdit.putString("name", data.getName());
                    myEdit.putString("email", data.getEmail());
                    myEdit.putInt("umur", data.getUmur());
                    myEdit.putString("nama_kontrakan", data.getNama_kontrakan());
                    myEdit.putInt("id", data.getId());

                    myEdit.apply();

                    if(role.equals("pemilik")){
                        SharedPreferences SP = getSharedPreferences("BigbossPreff",MODE_PRIVATE);
                        SharedPreferences.Editor ME = SP.edit();
                        ME.putInt("rooms", data.getRooms());
                        ME.apply();
                        Intent intent = new Intent(LoginActivity.this, PemilikHomeActivity.class);
                        startActivity(intent);
                    }else if(role.equals("pengontrak")){
                        Intent intent = new Intent(LoginActivity.this, PengontrakHomeActivity.class);


                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }



            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
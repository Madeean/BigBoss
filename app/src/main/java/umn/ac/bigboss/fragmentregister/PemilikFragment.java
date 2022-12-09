package umn.ac.bigboss.fragmentregister;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Response;
import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.modelauth.ValidationBEAuth;
import umn.ac.bigboss.pemilik.PemilikHomeActivity;


public class PemilikFragment extends Fragment {

    public EditText edit_text_nama_pemilik, edit_text_email_pemilik, edit_text_nama_kontrakan_pemilik
            ,edit_text_ruangan_pemilik,edit_text_password_pemilik;
    public Button btn_register;

    public String tokenFCM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pemilik, container, false);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("tokenFCM: "+token);
                        // Log and toast
                        tokenFCM = token;
                    }
                });



        edit_text_nama_pemilik = view.findViewById(R.id.edit_text_nama_pemilik);
        edit_text_email_pemilik = view.findViewById(R.id.edit_text_email_pemilik);
        edit_text_nama_kontrakan_pemilik = view.findViewById(R.id.edit_text_nama_kontrakan_pemilik);
        edit_text_ruangan_pemilik = view.findViewById(R.id.edit_text_ruangan_pemilik);
        edit_text_password_pemilik = view.findViewById(R.id.edit_text_password_pemilik);
        btn_register = view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(v -> {
            if(edit_text_nama_pemilik.getText().toString().trim().equals("")) {
                edit_text_nama_pemilik.setError("Nama tidak boleh kosong");
            }else if(edit_text_email_pemilik.getText().toString().trim().equals("")) {
                edit_text_email_pemilik.setError("Email tidak boleh kosong");
            }else if(edit_text_nama_kontrakan_pemilik.getText().toString().trim().equals("")) {
                edit_text_nama_kontrakan_pemilik.setError("Nama Kontrakan tidak boleh kosong");
            }else if(edit_text_ruangan_pemilik.getText().toString().trim().equals("")) {
                edit_text_ruangan_pemilik.setError("Ruangan tidak boleh kosong");
            }else if(edit_text_password_pemilik.getText().toString().trim().equals("")) {
                edit_text_password_pemilik.setError("Password tidak boleh kosong");
            }else{
                PostRegister();
            }


//            Intent intent = new Intent(getActivity(), PemilikHomeActivity.class);
//            startActivity(intent);

        });


        return view;

    }

    public void PostRegister(){
        String token = tokenFCM;
        String nama = edit_text_nama_pemilik.getText().toString();
        String email = edit_text_email_pemilik.getText().toString();
        String nama_kontrakan = edit_text_nama_kontrakan_pemilik.getText().toString();
        int rooms = Integer.parseInt(edit_text_ruangan_pemilik.getText().toString());
        String password = edit_text_password_pemilik.getText().toString();
        String role = "pemilik";
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<LoginModel> simpanData = api.ARRegisterPemilik(nama,email,password,rooms,role,nama_kontrakan,token);
        simpanData.enqueue(new retrofit2.Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    String token = response.body().getToken();
                    DataLoginModel data = response.body().getUser();
                    System.out.println("token : "+token);
                    if(token != null){
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("BigbossPreff",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("token", token);
                        myEdit.putString("role", role);
                        myEdit.putString("name", data.getName());
                        myEdit.putString("email", data.getEmail());
                        myEdit.putInt("umur", data.getUmur());
                        myEdit.putString("nama_kontrakan", data.getNama_kontrakan());
                        myEdit.putInt("rooms", data.getRooms());
                        myEdit.putString("tokenFCM", data.getTokenFCM());

                        myEdit.apply();

                        Intent intent = new Intent(getActivity(), PemilikHomeActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getActivity(), "gagal register email sudah terapakai", Toast.LENGTH_SHORT).show();
                    System.out.println("gagal register email sudah terapakai "+response.body());
                }


            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
    



}
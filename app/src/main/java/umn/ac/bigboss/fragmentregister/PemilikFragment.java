package umn.ac.bigboss.fragmentregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.pemilik.PemilikHomeActivity;


public class PemilikFragment extends Fragment {

    public EditText edit_text_nama_pemilik, edit_text_email_pemilik, edit_text_nama_kontrakan_pemilik
            ,edit_text_ruangan_pemilik,edit_text_password_pemilik;
    public Button btn_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pemilik, container, false);
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
        String nama = edit_text_nama_pemilik.getText().toString();
        String email = edit_text_email_pemilik.getText().toString();
        String nama_kontrakan = edit_text_nama_kontrakan_pemilik.getText().toString();
        int rooms = Integer.parseInt(edit_text_ruangan_pemilik.getText().toString());
        String password = edit_text_password_pemilik.getText().toString();
        String role = "pemilik";
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<LoginModel> simpanData = api.ARRegisterPemilik(nama,email,password,rooms,role,nama_kontrakan);
        simpanData.enqueue(new retrofit2.Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, retrofit2.Response<LoginModel> response) {
                String token = response.body().getToken();
                DataLoginModel data = response.body().getUser();
                System.out.println("token : "+token);
                if(token != null){
                    Intent intent = new Intent(getActivity(), PemilikHomeActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("gagal "+t.getMessage());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    



}
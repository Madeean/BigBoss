package umn.ac.bigboss.fragmentregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.NamaKontrakanModel;
import umn.ac.bigboss.modelauth.DataNamaKontrakanModel;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;


public class PengontrakFragment extends Fragment {

    ArrayList<String> list = new ArrayList<String>();
    String nama_kontrakan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pengontrak, container, false);
        list.add("Pilih Kontrakan");
        getNamaKontrakan();

        Spinner s = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                list.get(position);
                if(position >0 ){
                    Toast.makeText(getActivity(), list.get(position), Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(getActivity(), "Please select", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        Button btn_register_pengontrak = view.findViewById(R.id.btn_register_pengontrak);
        btn_register_pengontrak.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PengontrakHomeActivity.class);
            startActivity(intent);

        });

        return view;
    }



    public void getNamaKontrakan(){
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<DataNamaKontrakanModel> dataNamaKontrakan = api.ARNamaKontrakan();
        dataNamaKontrakan.enqueue(new retrofit2.Callback<DataNamaKontrakanModel>() {
            @Override
            public void onResponse(Call<DataNamaKontrakanModel> call, retrofit2.Response<DataNamaKontrakanModel> response) {
                if(response.isSuccessful()){

                    ArrayList<NamaKontrakanModel> data = (ArrayList<NamaKontrakanModel>) response.body().getUser();
                    for(int i = 0; i < data.size(); i++){
                        list.add(data.get(i).getNama_kontrakan());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataNamaKontrakanModel> call, Throwable t) {
                System.out.println("error "+t.getMessage());
                Toast.makeText(getActivity(), "Gagal mengambil data"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
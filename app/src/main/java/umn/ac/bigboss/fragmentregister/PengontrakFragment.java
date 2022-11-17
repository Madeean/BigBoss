package umn.ac.bigboss.fragmentregister;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
    Button btn_gallery_pengontrak,btn_camera_pengontrak;
    ImageView image_input_pengontrak;
    int SELECT_PICTURE = 200;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
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

        image_input_pengontrak = view.findViewById(R.id.image_input_pengontrak);
        btn_camera_pengontrak = view.findViewById(R.id.btn_camera_pengontrak);
        btn_gallery_pengontrak = view.findViewById(R.id.btn_gallery_pengontrak);
        btn_gallery_pengontrak.setOnClickListener(view1 -> {
           imageChooser();
        });
        btn_camera_pengontrak.setOnClickListener(view1 -> {
            if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    image_input_pengontrak.setImageURI(selectedImageUri);
                }

        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image_input_pengontrak.setImageBitmap(photo);
        }
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
package umn.ac.bigboss.fragmentregister;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.modelauth.NamaKontrakanModel;
import umn.ac.bigboss.modelauth.DataNamaKontrakanModel;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;


public class PengontrakFragment extends Fragment {

    ArrayList<String> list = new ArrayList<String>();

    Button btn_gallery_pengontrak,btn_camera_pengontrak;
    ImageView image_input_pengontrak;
    int SELECT_PICTURE = 200;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    File file;
    String filePath;
    File finalFile;

    String nama_kontrakan;
    EditText edit_text_nama_pengontrak,edit_text_email_pengontrak,edit_text_umur_pengontrak,edit_text_alamat_sesuai_ktp_pengontrak
            ,edit_text_alamat_kontrakan_sekarang_pengontrak,edit_text_harga_perbulan_pengontrak,edit_text_password_pengontrak;

    public String tokenFCM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pengontrak, container, false);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("token fcm : "+token);

                        // Log and toast
                        tokenFCM = token;
                    }
                });



        edit_text_nama_pengontrak = view.findViewById(R.id.edit_text_nama_pengontrak);
        edit_text_email_pengontrak = view.findViewById(R.id.edit_text_email_pengontrak);
        edit_text_umur_pengontrak = view.findViewById(R.id.edit_text_umur_pengontrak);
        edit_text_alamat_sesuai_ktp_pengontrak = view.findViewById(R.id.edit_text_alamat_sesuai_ktp_pengontrak);
        edit_text_alamat_kontrakan_sekarang_pengontrak = view.findViewById(R.id.edit_text_alamat_kontrakan_sekarang_pengontrak);
        edit_text_harga_perbulan_pengontrak = view.findViewById(R.id.edit_text_harga_perbulan_pengontrak);
        edit_text_password_pengontrak = view.findViewById(R.id.edit_text_password_pengontrak);



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
                    nama_kontrakan = list.get(position);
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
            if(nama_kontrakan == null){
                Toast.makeText(getActivity(), "Pilih Kontrakan", Toast.LENGTH_SHORT).show();
            }else if(edit_text_nama_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(edit_text_email_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(edit_text_umur_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Umur Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(edit_text_alamat_sesuai_ktp_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Alamat Sesuai KTP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(edit_text_alamat_kontrakan_sekarang_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Alamat Kontrakan Sekarang Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(edit_text_harga_perbulan_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Harga Perbulan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(finalFile == null) {
                Toast.makeText(getActivity(), "Foto Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(edit_text_password_pengontrak.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else{
                registerPengontrak();
            }

        });

        image_input_pengontrak = view.findViewById(R.id.image_input_pengontrak);
        btn_camera_pengontrak = view.findViewById(R.id.btn_camera_pengontrak);
        btn_gallery_pengontrak = view.findViewById(R.id.btn_gallery_pengontrak);
        btn_gallery_pengontrak.setOnClickListener(view1 -> {
            ImagePicker.Companion.with(this)
                    .galleryOnly()	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start(SELECT_PICTURE);

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

    private void registerPengontrak() {
        String token = tokenFCM;
        String nama = edit_text_nama_pengontrak.getText().toString();
        String email = edit_text_email_pengontrak.getText().toString();
        String password = edit_text_password_pengontrak.getText().toString();
        int umur = Integer.parseInt(edit_text_umur_pengontrak.getText().toString());
        String alamat_sesuai_ktp = edit_text_alamat_sesuai_ktp_pengontrak.getText().toString();
        String alamat_kontrakan_sekarang = edit_text_alamat_kontrakan_sekarang_pengontrak.getText().toString();
        int harga_perbulan = Integer.parseInt(edit_text_harga_perbulan_pengontrak.getText().toString());
        String nama_kontrakan = this.nama_kontrakan;
        String role = "pengontrak";
        File file = finalFile;
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("foto_muka", file.getName(), requestFile);

        RequestBody namaBody = RequestBody.create(MediaType.parse("multipart/form-data"), nama);
        RequestBody emailBody = RequestBody.create(MediaType.parse("multipart/form-data"), email);
        RequestBody passwordBody = RequestBody.create(MediaType.parse("multipart/form-data"), password);
        RequestBody umurBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(umur));
        RequestBody alamat_sesuai_ktpBody = RequestBody.create(MediaType.parse("multipart/form-data"), alamat_sesuai_ktp);
        RequestBody alamat_kontrakan_sekarangBody = RequestBody.create(MediaType.parse("multipart/form-data"), alamat_kontrakan_sekarang);
        RequestBody harga_perbulanBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(harga_perbulan));
        RequestBody nama_kontrakanBody = RequestBody.create(MediaType.parse("multipart/form-data"), nama_kontrakan);
        RequestBody roleBody = RequestBody.create(MediaType.parse("multipart/form-data"), role);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("multipart/form-data"), token);

        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<LoginModel> simpanData = api.ARRegisterPengontrak(body, namaBody, emailBody, passwordBody,umurBody,roleBody
        ,alamat_sesuai_ktpBody,alamat_kontrakan_sekarangBody,harga_perbulanBody,nama_kontrakanBody,tokenBody);

        simpanData.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    String token = response.body().getToken();
                    DataLoginModel data = response.body().getUser();

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("BigbossPreff",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("token", token);
                    myEdit.putString("role", role);
                    myEdit.putString("name", data.getName());
                    myEdit.putString("email", data.getEmail());
                    myEdit.putInt("umur", data.getUmur());
                    myEdit.putString("nama_kontrakan", data.getNama_kontrakan());
                    myEdit.putString("tokenFCM", data.getTokenFCM());

                    myEdit.apply();

                    Intent intent = new Intent(getActivity(), PengontrakHomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Gagal register email sudah terpakai" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });



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



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != getActivity().RESULT_CANCELED){
            if (resultCode == getActivity().RESULT_OK && requestCode == SELECT_PICTURE) {
                if(data != null){
                    Uri uri = data.getData();

                    file = ImagePicker.Companion.getFile(data);
                     filePath = ImagePicker.Companion.getFilePath(data);
                     Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    image_input_pengontrak.setImageBitmap(bitmap);

                    Uri tempUri = getImageUri(getActivity(), bitmap);
                    finalFile = new File(getRealPathFromURI(tempUri));

                }else{
                    Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                }


            }else if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                image_input_pengontrak.setImageBitmap(photo);

                Uri tempUri = getImageUri(getActivity(), photo);
                finalFile = new File(getRealPathFromURI(tempUri));
            }
        }else{
            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getActivity().getContentResolver() != null) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
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
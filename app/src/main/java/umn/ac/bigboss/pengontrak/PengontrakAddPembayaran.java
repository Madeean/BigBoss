package umn.ac.bigboss.pengontrak;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import retrofit2.Callback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;
import umn.ac.bigboss.RegisterActivity;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.modelauth.PembayaranModel;
import umn.ac.bigboss.pemilik.PemilikTambahPembayaran;

public class PengontrakAddPembayaran extends Fragment {
    Toolbar my_toolbar;
    TextView my_toolbar_title,edit_text_jumlah_bayar_add_pembayaran_pengontrak,text_tanggal_pembayaaran_pengontrak;
    public ImageView image_input_add_pembayaran_pengontrak;
    Button btn_camera_tambah_pembayaran_pengontrak,btn_gallery_tambah_pembayaran_pengontrak,
            btn_choose_date_add_pembayaran_pengontrak,btn_tambah_pembayaran_pengontrak;
    private Calendar calendar,calendar2;
    private SimpleDateFormat dateFormat,dateFormat2;
    private String date;
    private String pilih_tanggal_bayar;

    int SELECT_PICTURE = 200;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    File file;
    String filePath;
    File finalFile;

    DatePickerDialog datePickerDialog;

    int mYear,mMonth,mDay;
    public String role;
    public int pilih_bulan;

    public String namaSP,emailSP,nama_kontrakanSP;


    String token;

    ArrayList<String> list = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_pengontrak_add_pembayaran, container, false);
        edit_text_jumlah_bayar_add_pembayaran_pengontrak = view.findViewById(R.id.edit_text_jumlah_bayar_add_pembayaran_pengontrak);
        image_input_add_pembayaran_pengontrak = view.findViewById(R.id.image_input_add_pembayaran_pengontrak);
        btn_gallery_tambah_pembayaran_pengontrak = view.findViewById(R.id.btn_gallery_tambah_pembayaran_pengontrak);
        btn_camera_tambah_pembayaran_pengontrak = view.findViewById(R.id.btn_camera_tambah_pembayaran_pengontrak);

//        get bundle
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String uri = bundle.getString("uri");
            if(uri != null){
                handleSendImage(uri);
            }
        }


        list.clear();
        list.add("Pilih Bulan");
        list.add("Januari");
        list.add("Februari");
        list.add("Maret");
        list.add("April");
        list.add("Mei");
        list.add("Juni");
        list.add("Juli");
        list.add("Agustus");
        list.add("September");
        list.add("Oktober");
        list.add("November");
        list.add("Desember");


        Spinner s = (Spinner) view.findViewById(R.id.spinner_add_pembayaran_pengontrak);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(position >0 ){
                    pilih_bulan = position;
                }else{

                    Toast.makeText(getActivity(), "Please select", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


        SharedPreferences sh = getActivity().getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        token = sh.getString("token", "");
        int id = sh.getInt("id", 0);
        getName(id);


//         name = sh.getString("name", "");
//         email = sh.getString("email", "");
//        nkontrakan = sh.getString("nama_kontrakan", "");



        role = "pengontrak";
        calendar2 = Calendar.getInstance();
        dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        pilih_tanggal_bayar = dateFormat2.format(calendar2.getTime());


        btn_gallery_tambah_pembayaran_pengontrak.setOnClickListener(view1 -> {
            ImagePicker.Companion.with(this)
                    .galleryOnly()	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start(SELECT_PICTURE);
        });

        btn_camera_tambah_pembayaran_pengontrak.setOnClickListener(view1 -> {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        my_toolbar = view.findViewById(R.id.my_toolbar_add_pembayaran_pengontrak);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM");
        date = dateFormat.format(calendar.getTime());

        my_toolbar_title.setText("Pembayaran Bulan "+date);
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(my_toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        text_tanggal_pembayaaran_pengontrak = view.findViewById(R.id.text_tanggal_pembayaaran_pengontrak);
//        btn_choose_date_add_pembayaran_pengontrak = view.findViewById(R.id.btn_choose_date_add_pembayaran_pengontrak);
//        btn_choose_date_add_pembayaran_pengontrak.setOnClickListener(view1 -> {
//            final Calendar c = Calendar.getInstance();
//             mYear = c.get(Calendar.YEAR); // current year
//             mMonth = c.get(Calendar.MONTH); // current month
//             mDay = c.get(Calendar.DAY_OF_MONTH); // current day
//
//            datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//
//                        @Override
//                        public void onDateSet(DatePicker view, int year,
//                                              int monthOfYear, int dayOfMonth) {
//                            // set day of month , month and year value in the edit text
//                            text_tanggal_pembayaaran_pengontrak.setText(dayOfMonth + "-"
//                                    + (monthOfYear + 1) + "-" + year);
//                        }
//                    }, mYear, mMonth, mDay);
//            datePickerDialog.show();
//        });

        btn_tambah_pembayaran_pengontrak = view.findViewById(R.id.btn_tambah_pembayaran_pengontrak);
        btn_tambah_pembayaran_pengontrak.setOnClickListener(view1 -> {
            if(edit_text_jumlah_bayar_add_pembayaran_pengontrak.getText().toString().isEmpty()){
                edit_text_jumlah_bayar_add_pembayaran_pengontrak.setError("Jumlah Pembayaran Tidak Boleh Kosong");
            }else if(pilih_bulan == 0){
                Toast.makeText(getActivity(), "Pilih Bulan dahulu", Toast.LENGTH_SHORT).show();
            }else if(finalFile == null){
                Toast.makeText(getActivity(), "Gambar Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else{
                tambahPembayaran();
            }
        });


       return view;
    }

    private void handleSendImage(String uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image_input_add_pembayaran_pengontrak.setImageBitmap(bitmap);

        Uri tempUri = getImageUri(getActivity(), bitmap);
        finalFile = new File(getRealPathFromURI(tempUri));
    }

    private void getName(int id) {
        String token = "Bearer "+this.token;
        ApiRequest api  = Server.konekRetrofit().create(ApiRequest.class);
        Call<EditLogin> tampilData = api.ARDetailPengontrak(id,token);
        tampilData.enqueue(new Callback<EditLogin>() {
            @Override
            public void onResponse(Call<EditLogin> call, Response<EditLogin> response) {
                if (response.isSuccessful()){
                    String name = response.body().getUser().getName();
                    String email = response.body().getUser().getEmail();
                    String nama_kontrakan = response.body().getUser().getNama_kontrakan();
                    namaSP = name;
                    emailSP = email;
                    nama_kontrakanSP = nama_kontrakan;
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

    private void tambahPembayaran() {
        String nama_pengontrak = namaSP;
        String nama_kontrakan = nama_kontrakanSP;
        int bulan = pilih_bulan;
        int jumlah_bayar = Integer.parseInt(edit_text_jumlah_bayar_add_pembayaran_pengontrak.getText().toString());
        String tanggal_bayar = pilih_tanggal_bayar;
        String role = "pengontrak";
        File bukti_bayar = finalFile;

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bukti_bayar);
        MultipartBody.Part body = MultipartBody.Part.createFormData("bukti_bayar", bukti_bayar.getName(), requestFile);
        RequestBody namaBody = RequestBody.create(MediaType.parse("multipart/form-data"), nama_pengontrak);
        RequestBody namaKontrakanBody = RequestBody.create(MediaType.parse("multipart/form-data"), nama_kontrakan);
        RequestBody bulanBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(bulan));
        RequestBody jumlahBayarBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(jumlah_bayar));
        RequestBody tanggalBayarBody = RequestBody.create(MediaType.parse("multipart/form-data"), tanggal_bayar);
        RequestBody roleBody = RequestBody.create(MediaType.parse("multipart/form-data"), role);
        String tokenBody = "Bearer "+token;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);

        Call<PembayaranModel> simpanData = api.ARBayar(tokenBody,body, bulanBody,namaBody,tanggalBayarBody,jumlahBayarBody,roleBody,namaKontrakanBody );
        simpanData.enqueue(new Callback<PembayaranModel>() {
            @Override
            public void onResponse(Call<PembayaranModel> call, Response<PembayaranModel> response) {
                if(response.isSuccessful()){
                    String pesan = response.body().getMessage();
                    Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), PengontrakHomeActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getActivity(), "Gagal "+response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PembayaranModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    image_input_add_pembayaran_pengontrak.setImageBitmap(bitmap);

                    Uri tempUri = getImageUri(getActivity(), bitmap);
                    finalFile = new File(getRealPathFromURI(tempUri));

                }else{
                    Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                }


            }else if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                image_input_add_pembayaran_pengontrak.setImageBitmap(photo);

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


}
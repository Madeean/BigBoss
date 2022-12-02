package umn.ac.bigboss.pemilik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import umn.ac.bigboss.modelauth.GetUserKontrakanModel;
import umn.ac.bigboss.modelauth.PembayaranModel;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;

public class PemilikTambahPembayaran extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    Toolbar my_toolbar;
    TextView my_toolbar_title;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ArrayList<String> list = new ArrayList<String>();

    EditText et_jumlah_bayar_add_pembayaran_pemilik;
    ImageView image_input_add_pembayaran_pemilik;
    Button btn_camera_tambah_pembayaran_pemilik, btn_gallery_tambah_pembayaran_pemilik,btn_tambah_pembayaran_pemilik;


    String tokenSP,nama_kontrakanSP;

    int SELECT_PICTURE = 200;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    File file;
    String filePath;
    File finalFile;

    public int pilih_bulan;
    public String pilih_pengontrak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_tambah_pembayaran);

//        my_toolbar = findViewById(R.id.my_toolbar_tambah_pembayaran_pemilik);
//        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
//        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
//
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        date = dateFormat.format(calendar.getTime());
//
//        my_toolbar_title.setText("Pembayaran bulan "+date);
//        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
//        my_toolbar_title.setTextSize(16);
//        setSupportActionBar(my_toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        tokenSP = sh.getString("token", "");
        nama_kontrakanSP = sh.getString("nama_kontrakan", "");

        // Spinner element

          //make this as field atribute
        list.add("Pilih Nama Pengontrak");
        getNamaPengontrak();

        Spinner s = (Spinner) findViewById(R.id.spinner_pemilik);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                list.get(position);
                if(position >0 ){
                    Toast.makeText(PemilikTambahPembayaran.this, list.get(position), Toast.LENGTH_SHORT).show();
                    pilih_pengontrak = list.get(position);
                }else{

                    Toast.makeText(PemilikTambahPembayaran.this, "pilih nama pengontrak", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

//        END SPINNER

//        start spinner pilih bulan

        ArrayList<String> list_bulan = new ArrayList<String>(12);
        list_bulan.add("Pilih Bulan");
        list_bulan.add("Januari");
        list_bulan.add("Februari");
        list_bulan.add("Maret");
        list_bulan.add("April");
        list_bulan.add("Mei");
        list_bulan.add("Juni");
        list_bulan.add("Juli");
        list_bulan.add("Agustus");
        list_bulan.add("September");
        list_bulan.add("Oktober");
        list_bulan.add("November");
        list_bulan.add("Desember");
        Spinner s_bulan = (Spinner) findViewById(R.id.spinner_pilih_bulan_pemilik);
        ArrayAdapter<String> adapter_bulan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_bulan);
        adapter_bulan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_bulan.setAdapter(adapter_bulan);
        s_bulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(position >0 ){
                    pilih_bulan = position;

                }else{

                    Toast.makeText(PemilikTambahPembayaran.this, "Pilih bulan pembayaran", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });




        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        my_toolbar = findViewById(R.id.my_toolbar_tambah_pembayaran_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Pembayaran tanggal "+date);
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, my_toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.hitam));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sidebar_globe:
                        Intent intent4 = new Intent(PemilikTambahPembayaran.this, PemilikHomeActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.sidebar_tambah_pembayaran:
                        Intent intent = new Intent(PemilikTambahPembayaran.this, PemilikTambahPembayaran.class);
                        startActivity(intent);
                        break;
                    case R.id.sidebar_daftar_belum_lunas:
//                        Fragment fragment = new PemilikBelumBayarBulanan();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.container_pemilik, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                        Intent intent1 = new Intent(PemilikTambahPembayaran.this, PemilikDaftarBelumLunas.class);
                        startActivity(intent1);
                        break;
                    case R.id.sidebar_daftar_orang_ngontrak:
                        Intent intent2 = new Intent(PemilikTambahPembayaran.this, PemilikDaftarOrangNgontrak.class);
                        startActivity(intent2);
                        break;
                    case R.id.sidebar_request_pembayaran:
                        Intent intent3 = new Intent(PemilikTambahPembayaran.this, PemilikRequestPembayaran.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        et_jumlah_bayar_add_pembayaran_pemilik = findViewById(R.id.et_jumlah_bayar_add_pembayaran_pemilik);
        image_input_add_pembayaran_pemilik = findViewById(R.id.image_input_add_pembayaran_pemilik);
        btn_camera_tambah_pembayaran_pemilik = findViewById(R.id.btn_camera_tambah_pembayaran_pemilik);
        btn_gallery_tambah_pembayaran_pemilik = findViewById(R.id.btn_gallery_tambah_pembayaran_pemilik);
        btn_tambah_pembayaran_pemilik = findViewById(R.id.btn_tambah_pembayaran_pemilik);



        btn_gallery_tambah_pembayaran_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(PemilikTambahPembayaran.this)
                        .galleryOnly()	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(SELECT_PICTURE);
            }
        });

        btn_camera_tambah_pembayaran_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PemilikTambahPembayaran.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(PemilikTambahPembayaran.this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }

                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        btn_tambah_pembayaran_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_jumlah_bayar_add_pembayaran_pemilik.getText().toString().equals("")) {
                    Toast.makeText(PemilikTambahPembayaran.this, "Masukkan jumlah pembayaran", Toast.LENGTH_SHORT).show();
                } else if (finalFile == null) {
                    Toast.makeText(PemilikTambahPembayaran.this, "Masukkan bukti pembayaran", Toast.LENGTH_SHORT).show();
                } else {
                    TambahPembayaran();
                }
            }
        });


    }

    public void TambahPembayaran(){
        String tokenBody = "Bearer "+tokenSP;
        int jumlah_bayar = Integer.parseInt(et_jumlah_bayar_add_pembayaran_pemilik.getText().toString());
        String nama_kontrakan = nama_kontrakanSP;
        int bulan = pilih_bulan;
        String nama_pengontrak = pilih_pengontrak;
        String tanggal_bayar = date;
        String role = "pemilik";
        File bukti_bayar = finalFile;

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bukti_bayar);
        MultipartBody.Part body = MultipartBody.Part.createFormData("bukti_bayar", bukti_bayar.getName(), requestFile);
        RequestBody namaBody = RequestBody.create(MediaType.parse("multipart/form-data"), nama_pengontrak);
        RequestBody namaKontrakanBody = RequestBody.create(MediaType.parse("multipart/form-data"), nama_kontrakan);
        RequestBody bulanBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(bulan));
        RequestBody jumlahBayarBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(jumlah_bayar));
        RequestBody tanggalBayarBody = RequestBody.create(MediaType.parse("multipart/form-data"), tanggal_bayar);
        RequestBody roleBody = RequestBody.create(MediaType.parse("multipart/form-data"), role);

        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);

        Call<PembayaranModel> simpanData = api.ARBayar(tokenBody,body, bulanBody,namaBody,tanggalBayarBody,jumlahBayarBody,roleBody,namaKontrakanBody );

        simpanData.enqueue(new Callback<PembayaranModel>() {
            @Override
            public void onResponse(Call<PembayaranModel> call, Response<PembayaranModel> response) {
                if(response.isSuccessful()){
                    String pesan = response.body().getMessage();
                    Toast.makeText(PemilikTambahPembayaran.this, pesan, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PemilikTambahPembayaran.this, PemilikHomeActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(PemilikTambahPembayaran.this, "Gagal tambah pembayaran", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PembayaranModel> call, Throwable t) {
                Toast.makeText(PemilikTambahPembayaran.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(PemilikTambahPembayaran.this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(PemilikTambahPembayaran.this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != PemilikTambahPembayaran.this.RESULT_CANCELED){
            if (resultCode == PemilikTambahPembayaran.this.RESULT_OK && requestCode == SELECT_PICTURE) {
                if(data != null){
                    Uri uri = data.getData();

                    file = ImagePicker.Companion.getFile(data);
                    filePath = ImagePicker.Companion.getFilePath(data);
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    image_input_add_pembayaran_pemilik.setImageBitmap(bitmap);

                    Uri tempUri = getImageUri(PemilikTambahPembayaran.this, bitmap);
                    finalFile = new File(getRealPathFromURI(tempUri));

                }else{
                    Toast.makeText(PemilikTambahPembayaran.this, "Cancelled", Toast.LENGTH_SHORT).show();
                }


            }else if (requestCode == CAMERA_REQUEST && resultCode == PemilikTambahPembayaran.this.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                image_input_add_pembayaran_pemilik.setImageBitmap(photo);

                Uri tempUri = getImageUri(PemilikTambahPembayaran.this, photo);
                finalFile = new File(getRealPathFromURI(tempUri));
            }
        }else{
            Toast.makeText(PemilikTambahPembayaran.this, "Cancelled", Toast.LENGTH_SHORT).show();
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (PemilikTambahPembayaran.this.getContentResolver() != null) {
            Cursor cursor = PemilikTambahPembayaran.this.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    private void getNamaPengontrak() {
        String token = "Bearer "+ this.tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<GetUserKontrakanModel> simpanData = api.ARGetNamaPengontrak(token);
        simpanData.enqueue(new Callback<GetUserKontrakanModel>() {
            @Override
            public void onResponse(Call<GetUserKontrakanModel> call, Response<GetUserKontrakanModel> response) {
                if (response.isSuccessful()) {
                    List<DataLoginModel> dataLoginModel = response.body().getUser();
                    for (int i = 0; i < dataLoginModel.size(); i++) {
                        list.add(dataLoginModel.get(i).getName());
                    }
                } else {
                    Toast.makeText(PemilikTambahPembayaran.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserKontrakanModel> call, Throwable t) {
                Toast.makeText(PemilikTambahPembayaran.this, "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package umn.ac.bigboss.pemilik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.PembayaranModel;

public class PemilikDetailPengguna extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    ImageView image_view_detail_pengontrak;
    TextView text_nama_pengontrak_detail_pengguna,text_umur_detail_pengguna_pemilik,
            text_alamat_sesuai_ktp_detail_pengguna_pemilik,textalamat_kontrakan_sekarang_detail_pengguna,
            text_bayaran_perbulan_detail_pengguna,text_tanggal_bergabung_detail_pengguna;
    Button btn_hapus_pengguna;
    LinearLayout Yes,No;
    String tokenSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_detail_pengguna);
        my_toolbar = findViewById(R.id.my_toolbar_detail_pengguna_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        SharedPreferences sh = getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
        tokenSP = sh.getString("token", "");
        
//        get intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String foto_muka = intent.getStringExtra("foto_muka");
        int umur = intent.getIntExtra("umur",0);
        String umurSp = String.valueOf(umur);
        String alamat_ktp = intent.getStringExtra("alamat_ktp");
        String alamat_kontrakan = intent.getStringExtra("alamat_kontrakan");
        String harga_perbulan = intent.getStringExtra("harga_perbulan");
        String tanngal_bergabung = intent.getStringExtra("tanngal_bergabung");
        int id = intent.getIntExtra("id",0);
//
//
        image_view_detail_pengontrak = findViewById(R.id.image_view_detail_pengontrak);
        text_nama_pengontrak_detail_pengguna = findViewById(R.id.text_nama_pengontrak_detail_pengguna);
        text_umur_detail_pengguna_pemilik = findViewById(R.id.text_umur_detail_pengguna_pemilik);
        text_alamat_sesuai_ktp_detail_pengguna_pemilik = findViewById(R.id.text_alamat_sesuai_ktp_detail_pengguna_pemilik);
        textalamat_kontrakan_sekarang_detail_pengguna = findViewById(R.id.textalamat_kontrakan_sekarang_detail_pengguna);
        text_bayaran_perbulan_detail_pengguna = findViewById(R.id.text_bayaran_perbulan_detail_pengguna);
        text_tanggal_bergabung_detail_pengguna = findViewById(R.id.text_tanggal_bergabung_detail_pengguna);
//
//
        Glide.with(this)
                .load(foto_muka)
                .placeholder(R.drawable.kucing_topi)
                .into(image_view_detail_pengontrak);
//
        text_nama_pengontrak_detail_pengguna.setText(name);
        text_umur_detail_pengguna_pemilik.setText(umurSp);
        text_alamat_sesuai_ktp_detail_pengguna_pemilik.setText(alamat_ktp);
        textalamat_kontrakan_sekarang_detail_pengguna.setText(alamat_kontrakan);
        text_bayaran_perbulan_detail_pengguna.setText("Rp. "+harga_perbulan);
        text_tanggal_bergabung_detail_pengguna.setText(tanngal_bergabung);

        btn_hapus_pengguna = findViewById(R.id.btn_hapus_pengguna);
        btn_hapus_pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(false);
                bottomSheetDialog.show();

                Yes = bottomSheetDialog.findViewById(R.id.Yes);
                No = bottomSheetDialog.findViewById(R.id.No);

                No.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                Yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hapusPengontrak(id);
                        bottomSheetDialog.dismiss();
                    }
            });
            }

        });



        my_toolbar_title.setText("Detail Transaksi");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_hitam));

        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(PemilikDetailPengguna.this, PemilikDaftarOrangNgontrak.class);

                startActivity(inten);
            }
        });
    }

    private void hapusPengontrak(int id){
        String token = "Bearer "+tokenSP;
        ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
        Call<PembayaranModel> simpanData = api.ARHapusPengontrak(id,token);
        simpanData.enqueue(new Callback<PembayaranModel>() {
            @Override
            public void onResponse(Call<PembayaranModel> call, Response<PembayaranModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PemilikDetailPengguna.this, "Berhasil Menghapus Pengontrak", Toast.LENGTH_SHORT).show();
                    Intent inten = new Intent(PemilikDetailPengguna.this, PemilikDaftarOrangNgontrak.class);
                    startActivity(inten);
                }else{
                    Toast.makeText(PemilikDetailPengguna.this, "gagal menghapus pengontrak", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PembayaranModel> call, Throwable t) {
                Toast.makeText(PemilikDetailPengguna.this, "gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
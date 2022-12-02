package umn.ac.bigboss.pengontrak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import umn.ac.bigboss.R;

public class PengontrakDetailPembayaran extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengontrak_detail_pembayaran);
        ImageView image_detail_pembayaran_pengontrak = findViewById(R.id.image_detail_pembayaran_pengontrak);
        TextView text_nama_pengontrak_detail_pembayaran = findViewById(R.id.text_nama_pengontrak_detail_pembayaran);
        TextView text_bulan_detail_pembayaran_pengontrak = findViewById(R.id.text_bulan_detail_pembayaran_pengontrak);
        TextView text_tanggal_bayar_detail_pembayaran_pengontrak = findViewById(R.id.text_tanggal_bayar_detail_pembayaran_pengontrak);
        TextView text_jumlah_dibayar_detail_pembayaran_pengontrak = findViewById(R.id.text_jumlah_dibayar_detail_pembayaran_pengontrak);
        TextView text_jumlah_harus_dibayar_detail_pembayaran_pengontrak = findViewById(R.id.text_jumlah_harus_dibayar_detail_pembayaran_pengontrak);
        TextView text_status_bulan_ini_detail_pembayaran_pengontrak = findViewById(R.id.text_status_bulan_ini_detail_pembayaran_pengontrak);
        TextView text_alamat_kontrakan_detail_pembayaran_pengontrak = findViewById(R.id.text_alamat_kontrakan_detail_pembayaran_pengontrak);

//        get intent
        Intent intent = getIntent();
        String nama_pengontrak = intent.getStringExtra("nama_pengontrak");
        String alamat_pengontrak = intent.getStringExtra("alamat_pengontrak");
        String status_lunas = intent.getStringExtra("status_lunas");
        String jumlah_harus_bayar = intent.getStringExtra("jumlah_harus_bayar");
        int jumlah_bayar = intent.getIntExtra("jumlah_bayar",0);
        String tanggal_bayar = intent.getStringExtra("tanggal_bayar");
        int bulan = intent.getIntExtra("bulan",0);
        String bukti_bayar = intent.getStringExtra("bukti_bayar");

        System.out.println("bukti bayar: "+bukti_bayar);

        text_nama_pengontrak_detail_pembayaran.setText(nama_pengontrak);
        text_alamat_kontrakan_detail_pembayaran_pengontrak.setText(alamat_pengontrak);
        text_bulan_detail_pembayaran_pengontrak.setText("Bulan ke-"+bulan);
        text_tanggal_bayar_detail_pembayaran_pengontrak.setText(tanggal_bayar);
        text_jumlah_dibayar_detail_pembayaran_pengontrak.setText(String.valueOf(jumlah_bayar));
        text_jumlah_harus_dibayar_detail_pembayaran_pengontrak.setText(jumlah_harus_bayar);
        if(status_lunas.equals("LUNAS")){
            text_status_bulan_ini_detail_pembayaran_pengontrak.setTextColor(getResources().getColor(R.color.biru));
        }else{
            text_status_bulan_ini_detail_pembayaran_pengontrak.setTextColor(getResources().getColor(R.color.merah));
        }
        text_status_bulan_ini_detail_pembayaran_pengontrak.setText(status_lunas);

//        image_detail_pembayaran_pengontrak.setImageURI(Uri.parse("https://madeekan.madee.my.id/storage/pembayaran-images/468jcGbkTxc0XGaFZFqEog2kgBsIO9yb5nUa4BIp.jpg"));
        Glide.with(this).load(bukti_bayar).placeholder(R.drawable.kucing_topi).into(image_detail_pembayaran_pengontrak);



        my_toolbar = findViewById(R.id.my_toolbar_detail_pembayaran_pengontrak);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));


        my_toolbar_title.setText("Detail Transaksi");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_hitam));

        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(PengontrakDetailPembayaran.this, PengontrakHomeActivity.class);

                startActivity(inten);
            }
        });
    }
}
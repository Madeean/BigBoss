package umn.ac.bigboss.pemilik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import umn.ac.bigboss.R;

public class PemilikDetailTransaksiBelumLunas extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    ImageView image_view_belum_lunas;
    TextView text_nama_pengontrak_detail_pembayaran_pemilik,text_alamat_kontrakan_detail_pembayaran_pemilik,
            text_status_bulan_ini_detail_pembayaran_pemilik,text_jumlah_harus_dibayar_detail_pembayaran_pemilik,
            text_jumlah_dibayar_detail_pembayaran_pemilik,text_tanggal_pembayaran_belumlunas,text_pembayaran_bulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_detail_transaksi_belum_lunas);
        image_view_belum_lunas = findViewById(R.id.image_view_belum_lunas);
        text_nama_pengontrak_detail_pembayaran_pemilik = findViewById(R.id.text_nama_pengontrak_detail_pembayaran_pemilik);
        text_alamat_kontrakan_detail_pembayaran_pemilik = findViewById(R.id.text_alamat_kontrakan_detail_pembayaran_pemilik);
        text_status_bulan_ini_detail_pembayaran_pemilik = findViewById(R.id.text_status_bulan_ini_detail_pembayaran_pemilik);
        text_jumlah_harus_dibayar_detail_pembayaran_pemilik = findViewById(R.id.text_jumlah_harus_dibayar_detail_pembayaran_pemilik);
        text_jumlah_dibayar_detail_pembayaran_pemilik = findViewById(R.id.text_jumlah_dibayar_detail_pembayaran_pemilik);
        text_tanggal_pembayaran_belumlunas = findViewById(R.id.text_tanggal_pembayaran_belumlunas);
        text_pembayaran_bulan = findViewById(R.id.text_pembayaran_bulan);


        Intent intent = getIntent();
        String nama_pengontrak = intent.getStringExtra("nama_pengontrak");
        String alamat_pengontrak = intent.getStringExtra("alamat_pengontrak");
        String status_lunas = intent.getStringExtra("status_lunas");
        int jumlah_bayar = intent.getIntExtra("jumlah_bayar",0);
        String tanggal_bayar = intent.getStringExtra("tanggal_bayar");
        String bukti_pembayaran = intent.getStringExtra("bukti_pembayaran");
        String jumlah_yang_harus_dibayar = intent.getStringExtra("jumlah_yang_harus_dibayar");
        int bulan = intent.getIntExtra("bulan",0);

        Glide.with(this)
                .load(bukti_pembayaran)
                .placeholder(R.drawable.kucing_topi)
                .into(image_view_belum_lunas);
        text_nama_pengontrak_detail_pembayaran_pemilik.setText(nama_pengontrak);
        text_alamat_kontrakan_detail_pembayaran_pemilik.setText(alamat_pengontrak);
        text_status_bulan_ini_detail_pembayaran_pemilik.setText(status_lunas);
        text_jumlah_harus_dibayar_detail_pembayaran_pemilik.setText("Rp. "+jumlah_yang_harus_dibayar);
        text_jumlah_dibayar_detail_pembayaran_pemilik.setText("Rp. "+jumlah_bayar);
        text_tanggal_pembayaran_belumlunas.setText(tanggal_bayar);
        text_pembayaran_bulan.setText("Bulan ke-"+bulan);


        my_toolbar = findViewById(R.id.my_toolbar_detail_transaksi_belum_lunas_pemilik);
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
                Intent inten = new Intent(PemilikDetailTransaksiBelumLunas.this, PemilikDaftarBelumLunas.class);

                startActivity(inten);
            }
        });
    }
}
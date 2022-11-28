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

public class PemilikDetailTransaksiMenungguKonfirmasiPemilik extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title,text_nama_pengontrak_detail_pembayaran_menunggu_pemilik,
            text_alamat_kontrakan_detail_pembayaran_menunggu_pemilik,text_status_bulan_ini_detail_pembayaran_menunggu_pemilik,
            text_jumlah_harus_dibayar_detail_pembayaran_menunggu_pemilik,text_jumlah_dibayar_detail_pembayaran_menuggu_pemilik,
            text_tanggal_bayar_detail_transaksi_request_pemilik,bulan_detail_transaksi_request_pemilik;
    ImageView iv_detail_transaksi_request_pemilik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_detail_transaksi_menunggu_konfirmasi_pemilik);
        my_toolbar = findViewById(R.id.my_toolbar_detail_transaksi_menuggu_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        Intent intent = getIntent();
        String nama_pengontrak = intent.getStringExtra("nama_pengontrak");
        String alamat_kontrakan_sekarang = intent.getStringExtra("alamat_kontrakan_sekarang");
        String status_lunas = intent.getStringExtra("status_lunas");
        String jumlah_yang_harus_dibayar = intent.getStringExtra("jumlah_yang_harus_dibayar");
        int jumlah_yang_dibayar = intent.getIntExtra("jumlah_yang_dibayar",0);
        String bukti_bayar = intent.getStringExtra("bukti_bayar");
        String tanggal_bayar = intent.getStringExtra("tanggal_bayar");
        int bulanSP= intent.getIntExtra("bulan",0);
        String bulan = "Bulan ke-"+bulanSP;


        iv_detail_transaksi_request_pemilik = findViewById(R.id.iv_detail_transaksi_request_pemilik);
        text_nama_pengontrak_detail_pembayaran_menunggu_pemilik = findViewById(R.id.text_nama_pengontrak_detail_pembayaran_menunggu_pemilik);
        text_alamat_kontrakan_detail_pembayaran_menunggu_pemilik = findViewById(R.id.text_alamat_kontrakan_detail_pembayaran_menunggu_pemilik);
        text_status_bulan_ini_detail_pembayaran_menunggu_pemilik = findViewById(R.id.text_status_bulan_ini_detail_pembayaran_menunggu_pemilik);
        text_jumlah_harus_dibayar_detail_pembayaran_menunggu_pemilik = findViewById(R.id.text_jumlah_harus_dibayar_detail_pembayaran_menunggu_pemilik);
        text_jumlah_dibayar_detail_pembayaran_menuggu_pemilik = findViewById(R.id.text_jumlah_dibayar_detail_pembayaran_menuggu_pemilik);
        text_tanggal_bayar_detail_transaksi_request_pemilik = findViewById(R.id.text_tanggal_bayar_detail_transaksi_request_pemilik);
        bulan_detail_transaksi_request_pemilik = findViewById(R.id.bulan_detail_transaksi_request_pemilik);

        Glide.with(this).load(bukti_bayar).placeholder(R.drawable.kucing_topi).into(iv_detail_transaksi_request_pemilik);
        text_nama_pengontrak_detail_pembayaran_menunggu_pemilik.setText(nama_pengontrak);
        text_alamat_kontrakan_detail_pembayaran_menunggu_pemilik.setText(alamat_kontrakan_sekarang);
text_status_bulan_ini_detail_pembayaran_menunggu_pemilik.setText(status_lunas);
        text_jumlah_harus_dibayar_detail_pembayaran_menunggu_pemilik.setText("Rp. "+jumlah_yang_harus_dibayar);
        text_jumlah_dibayar_detail_pembayaran_menuggu_pemilik.setText("Rp. "+String.valueOf(jumlah_yang_dibayar));
        text_tanggal_bayar_detail_transaksi_request_pemilik.setText(tanggal_bayar);
        bulan_detail_transaksi_request_pemilik.setText(bulan);



        my_toolbar_title.setText("Detail Transaksi");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_hitam));

        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(PemilikDetailTransaksiMenungguKonfirmasiPemilik.this, PemilikRequestPembayaran.class);

                startActivity(inten);
            }
        });
    }
}
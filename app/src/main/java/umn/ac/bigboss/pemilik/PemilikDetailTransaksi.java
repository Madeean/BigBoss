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
import umn.ac.bigboss.pengontrak.PengontrakDetailPembayaran;
import umn.ac.bigboss.pengontrak.PengontrakHomeActivity;

public class PemilikDetailTransaksi extends AppCompatActivity {
    Toolbar my_toolbar;
    TextView my_toolbar_title;
    ImageView iv_detail_transaksi_pemilik;
    TextView text_nama_pengontrak_detail_pembayaran_pemilik,text_alamat_kontrakan_detail_pembayaran_pemilik,text_status_bulan_ini_detail_pembayaran_pemilik,
            text_jumlah_harus_dibayar_detail_pembayaran_pemilik,text_jumlah_dibayar_detail_pembayaran_pemilik,tanggal_bayar_detail_transaksi_pemilik,
            bulan_detail_transaksi_pemilik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_detail_transaksi);
        my_toolbar = findViewById(R.id.my_toolbar_detail_transaksi_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        iv_detail_transaksi_pemilik = findViewById(R.id.iv_detail_transaksi_pemilik);
text_nama_pengontrak_detail_pembayaran_pemilik = findViewById(R.id.text_nama_pengontrak_detail_pembayaran_pemilik);
text_alamat_kontrakan_detail_pembayaran_pemilik = findViewById(R.id.text_alamat_kontrakan_detail_pembayaran_pemilik);
text_status_bulan_ini_detail_pembayaran_pemilik = findViewById(R.id.text_status_bulan_ini_detail_pembayaran_pemilik);
text_jumlah_harus_dibayar_detail_pembayaran_pemilik = findViewById(R.id.text_jumlah_harus_dibayar_detail_pembayaran_pemilik);
text_jumlah_dibayar_detail_pembayaran_pemilik = findViewById(R.id.text_jumlah_dibayar_detail_pembayaran_pemilik);
tanggal_bayar_detail_transaksi_pemilik = findViewById(R.id.tanggal_bayar_detail_transaksi_pemilik);
bulan_detail_transaksi_pemilik = findViewById(R.id.bulan_detail_transaksi_pemilik);


//        get intent
        Intent intent = getIntent();
        String nama_pengontrak = intent.getStringExtra("nama_pengontrak");
        String alamat_pengontrak = intent.getStringExtra("alamat_pengontrak");
        String status_lunas = intent.getStringExtra("status_lunas");
        int jumlah_bayar = intent.getIntExtra("jumlah_bayar",0);
        String tanggal_bayar = intent.getStringExtra("tanggal_bayar");
        String bukti_pembayaran = intent.getStringExtra("bukti_pembayaran");
        String jumlah_yang_harus_dibayar = intent.getStringExtra("jumlah_yang_harus_dibayar");
        int bulan = intent.getIntExtra("bulan",0);

        Glide.with(this).load(bukti_pembayaran).placeholder(R.drawable.kucing_topi).into(iv_detail_transaksi_pemilik);
        text_nama_pengontrak_detail_pembayaran_pemilik.setText(nama_pengontrak);
        text_alamat_kontrakan_detail_pembayaran_pemilik.setText(alamat_pengontrak);
        if(status_lunas.equals("LUNAS")){
            text_status_bulan_ini_detail_pembayaran_pemilik.setTextColor(getResources().getColor(R.color.biru));
        }else{
            text_status_bulan_ini_detail_pembayaran_pemilik.setTextColor(getResources().getColor(R.color.merah));
        }
        text_status_bulan_ini_detail_pembayaran_pemilik.setText(status_lunas);
        text_jumlah_harus_dibayar_detail_pembayaran_pemilik.setText("Rp. "+jumlah_yang_harus_dibayar);
        text_jumlah_dibayar_detail_pembayaran_pemilik.setText("Rp. "+jumlah_bayar);
        tanggal_bayar_detail_transaksi_pemilik.setText(tanggal_bayar);
        bulan_detail_transaksi_pemilik.setText("bulan ke- "+bulan);




        my_toolbar_title.setText("Detail Transaksi");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_hitam));

        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(PemilikDetailTransaksi.this, PemilikHomeActivity.class);

                startActivity(inten);
            }
        });

    }
}
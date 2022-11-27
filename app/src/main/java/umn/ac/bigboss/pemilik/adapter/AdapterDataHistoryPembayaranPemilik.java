package umn.ac.bigboss.pemilik.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.PemilikDetailTransaksi;
import umn.ac.bigboss.pengontrak.PengontrakDetailPembayaran;
import umn.ac.bigboss.pengontrak.adapter.adapter_data_history_pembayaran_pengontrak;

public class AdapterDataHistoryPembayaranPemilik extends RecyclerView.Adapter<AdapterDataHistoryPembayaranPemilik.HolderData>{
    List<RequestPembayaranPengontrakmodel> listData;
    LayoutInflater layoutInflater;


    public AdapterDataHistoryPembayaranPemilik(Context context, List<RequestPembayaranPengontrakmodel> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public AdapterDataHistoryPembayaranPemilik.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pemilik_history_pembayaran, parent, false);
        return new AdapterDataHistoryPembayaranPemilik.HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataHistoryPembayaranPemilik.HolderData holder, int position) {
        holder.nama_pengontrak.setText(listData.get(position).getNama_pengontrak());
        holder.alamat_pengontrak.setText(listData.get(position).getUser().get(0).getAlamat_kontrakan_sekarang());
        holder.tanggal_bayar.setText(listData.get(position).getTanggal_bayar());
        holder.status_lunas.setTextColor(listData.get(position).getStatus_lunas().equals("LUNAS") ? 0xff5066CE : 0xffF55C5C);
        holder.status_lunas.setText(listData.get(position).getStatus_lunas());
        String jumlahBayar = "Rp. " + listData.get(position).getJumlah_bayar();
        System.out.println("jumlah bayar : " + jumlahBayar);
        holder.bayar_pengontrak.setText(jumlahBayar);
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PemilikDetailTransaksi.class);
                intent.putExtra("id", listData.get(position).getId());
                intent.putExtra("nama_pengontrak", listData.get(position).getNama_pengontrak());
                intent.putExtra("alamat_pengontrak", listData.get(position).getUser().get(0).getAlamat_kontrakan_sekarang());
                intent.putExtra("status_lunas", listData.get(position).getStatus_lunas());
                intent.putExtra("jumlah_bayar", listData.get(position).getJumlah_bayar());
                intent.putExtra("tanggal_bayar", listData.get(position).getTanggal_bayar());
                intent.putExtra("bukti_pembayaran", listData.get(position).getBukti_bayar());
                intent.putExtra("jumlah_yang_harus_dibayar",listData.get(position).getUser().get(0).getHarga_perbulan());
                intent.putExtra("bulan",listData.get(position).getBulan());

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView nama_pengontrak,alamat_pengontrak,bayar_pengontrak, status_lunas, tanggal_bayar;
        Button btn_detail;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            nama_pengontrak = itemView.findViewById(R.id.nama_pengontrak_card);
            alamat_pengontrak = itemView.findViewById(R.id.alamat_pengontrak_card);
            bayar_pengontrak = itemView.findViewById(R.id.bayar_pengontrak_card_pemilik);
            status_lunas = itemView.findViewById(R.id.status_lunas_pengontrak_card);
            tanggal_bayar = itemView.findViewById(R.id.tanggal_bayar_pengontrak_card);
            btn_detail = itemView.findViewById(R.id.btn_detail_transaksi);

        }
    }
}

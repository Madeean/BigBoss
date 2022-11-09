package umn.ac.bigboss.pemilik.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.pemilik.PemilikDetailTransaksi;
import umn.ac.bigboss.pengontrak.PengontrakDetailPembayaran;
import umn.ac.bigboss.pengontrak.adapter.adapter_data_history_pembayaran_pengontrak;

public class AdapterDataHistoryPembayaranPemilik extends RecyclerView.Adapter<AdapterDataHistoryPembayaranPemilik.HolderData>{
    List<String> listData;
    LayoutInflater layoutInflater;


    public AdapterDataHistoryPembayaranPemilik(Context context, List<String> listData) {
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
        holder.nama_pengontrak.setText(listData.get(position));
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PemilikDetailTransaksi.class);
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
            bayar_pengontrak = itemView.findViewById(R.id.bayar_pengontrak_card);
            status_lunas = itemView.findViewById(R.id.status_lunas_pengontrak_card);
            tanggal_bayar = itemView.findViewById(R.id.tanggal_bayar_pengontrak_card);
            btn_detail = itemView.findViewById(R.id.btn_detail_transaksi);

        }
    }
}

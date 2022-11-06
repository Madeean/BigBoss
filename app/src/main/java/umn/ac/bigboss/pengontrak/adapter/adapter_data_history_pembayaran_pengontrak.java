package umn.ac.bigboss.pengontrak.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import umn.ac.bigboss.R;

public class adapter_data_history_pembayaran_pengontrak extends RecyclerView.Adapter<adapter_data_history_pembayaran_pengontrak.HolderData>{
    List<String> listData;
    LayoutInflater layoutInflater;


    public adapter_data_history_pembayaran_pengontrak(Context context, List<String> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public adapter_data_history_pembayaran_pengontrak.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pengontrak_history_pembayaran, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_data_history_pembayaran_pengontrak.HolderData holder, int position) {
        holder.nama_pengontrak.setText(listData.get(position));

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

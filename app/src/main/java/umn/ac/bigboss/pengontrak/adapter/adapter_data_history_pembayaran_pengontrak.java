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
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pengontrak.PengontrakDetailPembayaran;

public class adapter_data_history_pembayaran_pengontrak extends RecyclerView.Adapter<adapter_data_history_pembayaran_pengontrak.HolderData>{
    List<RequestPembayaranPengontrakmodel> listData;
    LayoutInflater layoutInflater;


    public adapter_data_history_pembayaran_pengontrak(Context context, List<RequestPembayaranPengontrakmodel> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public adapter_data_history_pembayaran_pengontrak.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pengontrak_history_pembayaran, parent, false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_data_history_pembayaran_pengontrak.HolderData holder, int position) {
        holder.nama_pengontrak.setText(listData.get(position).getNama_pengontrak());
        holder.alamat_pengontrak.setText(listData.get(position).getUser().get(0).getAlamat_kontrakan_sekarang());
        holder.tanggal_bayar.setText(listData.get(position).getTanggal_bayar());
        holder.status_lunas.setText(listData.get(position).getStatus_lunas());
        holder.status_lunas.setTextColor(listData.get(position).getStatus_lunas().equals("LUNAS") ? 0xff5066CE : 0xffF55C5C);
        holder.bayar_pengontrak.setText("Rp. "+listData.get(position).getJumlah_bayar());
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PengontrakDetailPembayaran.class);
                intent.putExtra("id", listData.get(position).getId());
                intent.putExtra("nama_pengontrak", listData.get(position).getNama_pengontrak());
                intent.putExtra("alamat_pengontrak", listData.get(position).getUser().get(0).getAlamat_kontrakan_sekarang());
                intent.putExtra("status_lunas", listData.get(position).getStatus_lunas());
                intent.putExtra("jumlah_harus_bayar", listData.get(position).getUser().get(0).getHarga_perbulan());
                intent.putExtra("jumlah_bayar", listData.get(position).getJumlah_bayar());
                intent.putExtra("tanggal_bayar", listData.get(position).getTanggal_bayar());
                intent.putExtra("bulan", listData.get(position).getBulan());
                intent.putExtra("bukti_bayar", listData.get(position).getBukti_bayar());
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

    public void filterList(List<RequestPembayaranPengontrakmodel> filteredList) {
        listData = filteredList;
        notifyDataSetChanged();
    }
}

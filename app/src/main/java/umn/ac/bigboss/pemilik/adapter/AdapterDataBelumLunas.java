package umn.ac.bigboss.pemilik.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.PemilikDetailTransaksi;
import umn.ac.bigboss.pemilik.PemilikDetailTransaksiBelumLunas;

public class AdapterDataBelumLunas extends RecyclerView.Adapter<AdapterDataBelumLunas.HolderData>{
        List<RequestPembayaranPengontrakmodel> listData;
        LayoutInflater layoutInflater;


public AdapterDataBelumLunas(Context context, List<RequestPembayaranPengontrakmodel> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

        }

    @NonNull
    @Override
    public AdapterDataBelumLunas.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pemilik_belum_lunas, parent, false);
        return new AdapterDataBelumLunas.HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataBelumLunas.HolderData holder, int position) {
            holder.text_nama_belum_lunas.setText(listData.get(position).getNama_pengontrak());
            holder.text_alamat_belum_lunas.setText(listData.get(position).getUser().get(0).getAlamat_kontrakan_sekarang());
            holder.text_status_lunas_belum_lunas.setText(listData.get(position).getStatus_lunas());
            int bayar = listData.get(position).getJumlah_bayar();
            String harus_bayar = listData.get(position).getUser().get(0).getHarga_perbulan();
            holder.text_jumlah_bayar_belum_lunas.setText("Rp. "+bayar + " / " + "Rp. "+harus_bayar );

        holder.btn_detail_transaksi_belum_lunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), PemilikDetailTransaksiBelumLunas.class);
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
        Button btn_detail_transaksi_belum_lunas;
        TextView text_nama_belum_lunas, text_alamat_belum_lunas,text_status_lunas_belum_lunas,text_jumlah_bayar_belum_lunas;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            btn_detail_transaksi_belum_lunas = itemView.findViewById(R.id.btn_detail_transaksi_belum_lunas);
            text_nama_belum_lunas = itemView.findViewById(R.id.text_nama_belum_lunas);
            text_alamat_belum_lunas = itemView.findViewById(R.id.text_alamat_belum_lunas);
            text_status_lunas_belum_lunas = itemView.findViewById(R.id.text_status_lunas_belum_lunas);
            text_jumlah_bayar_belum_lunas = itemView.findViewById(R.id.text_jumlah_bayar_belum_lunas);


        }
    }
}

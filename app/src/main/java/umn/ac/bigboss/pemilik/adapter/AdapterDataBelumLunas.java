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
import umn.ac.bigboss.pemilik.PemilikDetailTransaksi;
import umn.ac.bigboss.pemilik.PemilikDetailTransaksiBelumLunas;

public class AdapterDataBelumLunas extends RecyclerView.Adapter<AdapterDataBelumLunas.HolderData>{
        List<String> listData;
        LayoutInflater layoutInflater;


public AdapterDataBelumLunas(Context context, List<String> listData) {
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
            holder.text_nama_belum_lunas.setText(listData.get(position));
        holder.btn_detail_transaksi_belum_lunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), PemilikDetailTransaksiBelumLunas.class);
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

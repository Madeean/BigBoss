package umn.ac.bigboss.pemilik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.modelauth.DataLoginModel;

public class AdapterDataBelumBayarBulanan extends RecyclerView.Adapter<AdapterDataBelumBayarBulanan.HolderData>{
    List<DataLoginModel> listData;
    LayoutInflater layoutInflater;
    int bulan;


    public AdapterDataBelumBayarBulanan(Context context, List<DataLoginModel> listData,int bulan) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
        this.bulan = bulan;
    }

    @NonNull
    @Override
    public AdapterDataBelumBayarBulanan.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pemilik_belum_bayar_bulanan, parent, false);
        return new AdapterDataBelumBayarBulanan.HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataBelumBayarBulanan.HolderData holder, int position) {
        holder.text_bulan_pemilik_belum_bayar_bulanan.setText("bulan ke - "+bulan);
        holder.text_name_pemilik_belum_bayar_bulanan.setText(listData.get(position).getName());
        Glide.with(holder.imageView.getContext()).load(listData.get(position).getFoto_muka()).placeholder(R.drawable.kucing_topi).into(holder.imageView);
//        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), PengontrakDetailPembayaran.class);
//                v.getContext().startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        if(listData != null){
            return listData.size();
        }else{
            return 0;
        }
    }

    public class HolderData extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text_name_pemilik_belum_bayar_bulanan, text_bulan_pemilik_belum_bayar_bulanan;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_pemilik_belum_bayar_bulanan);
            text_name_pemilik_belum_bayar_bulanan = itemView.findViewById(R.id.text_name_pemilik_belum_bayar_bulanan);
            text_bulan_pemilik_belum_bayar_bulanan = itemView.findViewById(R.id.text_bulan_pemilik_belum_bayar_bulanan);

        }
    }
}

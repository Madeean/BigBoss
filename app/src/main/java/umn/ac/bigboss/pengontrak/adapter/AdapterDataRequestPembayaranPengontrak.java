package umn.ac.bigboss.pengontrak.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;

public class AdapterDataRequestPembayaranPengontrak extends RecyclerView.Adapter<AdapterDataRequestPembayaranPengontrak.HolderData>{
    List<RequestPembayaranPengontrakmodel> listData;
    LayoutInflater layoutInflater;


    public AdapterDataRequestPembayaranPengontrak(Context context, List<RequestPembayaranPengontrakmodel> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public AdapterDataRequestPembayaranPengontrak.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pengontrak_request_pembayaran, parent, false);
        return new AdapterDataRequestPembayaranPengontrak.HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataRequestPembayaranPengontrak.HolderData holder, int position) {
        holder.text_name_request_pengontrak.setText(listData.get(position).getNama_pengontrak());
        holder.text_bulan_request_pengontrak.setText("bulan ke - "+listData.get(position).getBulan());
        holder.text_status_konfirmasi_request_pengontrak.setText(listData.get(position).getStatus_konfirmasi());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView text_name_request_pengontrak,text_bulan_request_pengontrak,text_status_konfirmasi_request_pengontrak;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            text_name_request_pengontrak = itemView.findViewById(R.id.text_name_request_pengontrak);
            text_bulan_request_pengontrak = itemView.findViewById(R.id.text_bulan_request_pengontrak);
            text_status_konfirmasi_request_pengontrak = itemView.findViewById(R.id.text_status_konfirmasi_request_pengontrak);

        }
    }
}

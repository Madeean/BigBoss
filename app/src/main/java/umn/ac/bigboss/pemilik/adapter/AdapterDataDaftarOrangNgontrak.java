package umn.ac.bigboss.pemilik.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import umn.ac.bigboss.R;
import umn.ac.bigboss.pemilik.PemilikDetailPengguna;

public class AdapterDataDaftarOrangNgontrak extends RecyclerView.Adapter<AdapterDataDaftarOrangNgontrak.HolderData> {
    int [] arr;


    public AdapterDataDaftarOrangNgontrak(int[] arr) {
        this.arr = arr;
    }


    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_pemilik_daftar_orang_ngontrak,parent,false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        holder.imageView.setImageResource(arr[position]);
        holder.textView.setText("dwi Rianto");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PemilikDetailPengguna.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    public class HolderData extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_daftar_orang_ngontrak_pemilik);
            textView = itemView.findViewById(R.id.tv_nama_daftar_orang_ngontrak_pemilik);
        }
    }
}

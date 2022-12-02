package umn.ac.bigboss.pemilik.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import umn.ac.bigboss.R;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.pemilik.PemilikDetailPengguna;

public class AdapterDataDaftarOrangNgontrak extends RecyclerView.Adapter<AdapterDataDaftarOrangNgontrak.HolderData> {
    List<DataLoginModel> listData;


    public AdapterDataDaftarOrangNgontrak(List<DataLoginModel> listData) {
        this.listData = listData;
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
        Glide.with(holder.itemView.getContext())
                .load(listData.get(position).getFoto_muka())
                .into(holder.imageView);

        holder.textView.setText(listData.get(position).getName());
//        holder.imageView.setImageResource(arr[position]);
//        holder.textView.setText("dwi Rianto");
//
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PemilikDetailPengguna.class);
                intent.putExtra("id",listData.get(position).getId());
                intent.putExtra("foto_muka",listData.get(position).getFoto_muka());
                intent.putExtra("name",listData.get(position).getName());
                intent.putExtra("umur",listData.get(position).getUmur());
                intent.putExtra("alamat_ktp",listData.get(position).getAlamat_sesuai_ktp());
                intent.putExtra("alamat_kontrakan",listData.get(position).getAlamat_kontrakan_sekarang());
                intent.putExtra("harga_perbulan",listData.get(position).getHarga_perbulan());
                intent.putExtra("tanngal_bergabung",listData.get(position).getCreated());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listData.size() > 0){
            return listData.size();
        }else {
            return 0;
        }
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

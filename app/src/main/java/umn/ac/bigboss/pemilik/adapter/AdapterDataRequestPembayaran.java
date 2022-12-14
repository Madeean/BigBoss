package umn.ac.bigboss.pemilik.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.bigboss.R;
import umn.ac.bigboss.api.ApiRequest;
import umn.ac.bigboss.api.Server;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.RequestPembayaranPengontrakmodel;
import umn.ac.bigboss.pemilik.PemilikDetailTransaksiMenungguKonfirmasiPemilik;
import umn.ac.bigboss.pemilik.PemilikHomeActivity;

public class AdapterDataRequestPembayaran extends RecyclerView.Adapter<AdapterDataRequestPembayaran.HolderData>{

    List<RequestPembayaranPengontrakmodel> listData;
    LayoutInflater layoutInflater;
    Context ctx;


    public AdapterDataRequestPembayaran(Context context, List<RequestPembayaranPengontrakmodel> listData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.ctx = context;

    }

    @NonNull
    @Override
    public AdapterDataRequestPembayaran.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.data_pemilik_request_pembayaran, parent, false);
        return new AdapterDataRequestPembayaran.HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataRequestPembayaran.HolderData holder, int position) {
        holder.nama_request_pembayaran.setText(listData.get(position).getNama_pengontrak());
        holder.bulan_request_pembayaran.setText("Bulan ke - "+listData.get(position).getBulan());
//        holder.nama_request_pembayaran.setText(listData.get(position));
        holder.btn_detail_transaksi_request_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PemilikDetailTransaksiMenungguKonfirmasiPemilik.class);
                intent.putExtra("nama_pengontrak", listData.get(position).getNama_pengontrak());
                intent.putExtra("alamat_kontrakan_sekarang",listData.get(position).getUser().get(0).getAlamat_kontrakan_sekarang());
                intent.putExtra("status_lunas",listData.get(position).getStatus_konfirmasi());
                intent.putExtra("jumlah_yang_harus_dibayar",listData.get(position).getUser().get(0).getHarga_perbulan());
                intent.putExtra("jumlah_yang_dibayar",listData.get(position).getJumlah_bayar());
                intent.putExtra("bukti_bayar",listData.get(position).getBukti_bayar());
                intent.putExtra("tanggal_bayar",listData.get(position).getTanggal_bayar());
                intent.putExtra("bulan",listData.get(position).getBulan());

                v.getContext().startActivity(intent);
            }
        });

        holder.btn_terima_request_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token ="Bearer "+holder.tokenSP;
                int id = listData.get(position).getId();
                ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
                Call<EditLogin> simpanData = api.ARTerimaPembayaran(id,token);
                simpanData.enqueue(new Callback<EditLogin>() {
                    @Override
                    public void onResponse(Call<EditLogin> call, Response<EditLogin> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ctx, "berhasil terima pembayaran", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(), PemilikHomeActivity.class);
                            view.getContext().startActivity(intent);
                        }else{
                            Toast.makeText(ctx, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EditLogin> call, Throwable t) {
                        Toast.makeText(ctx, "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.btn_tolak_request_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token ="Bearer "+holder.tokenSP;
                int id = listData.get(position).getId();
                ApiRequest api = Server.konekRetrofit().create(ApiRequest.class);
                Call<EditLogin> simpanData = api.ARTolakPembayaran(id,token);
                simpanData.enqueue(new Callback<EditLogin>() {
                    @Override
                    public void onResponse(Call<EditLogin> call, Response<EditLogin> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(view.getContext(), PemilikHomeActivity.class);
                            view.getContext().startActivity(intent);
                        }else{
                            Toast.makeText(ctx, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EditLogin> call, Throwable t) {
                        Toast.makeText(ctx, "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }



    public class HolderData extends RecyclerView.ViewHolder{
       TextView nama_request_pembayaran,bulan_request_pembayaran;
       Button btn_terima_request_pembayaran,btn_tolak_request_pembayaran,btn_detail_transaksi_request_pembayaran;
        String tokenSP;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            SharedPreferences sh = ctx.getSharedPreferences("BigbossPreff", Context.MODE_WORLD_READABLE);
            tokenSP = sh.getString("token", "");
            nama_request_pembayaran = itemView.findViewById(R.id.nama_request_pembayaran);
            bulan_request_pembayaran = itemView.findViewById(R.id.bulan_request_pembayaran);
            btn_detail_transaksi_request_pembayaran = itemView.findViewById(R.id.btn_detail_transaksi_request_pembayaran);
            btn_terima_request_pembayaran = itemView.findViewById(R.id.btn_terima_request_pembayaran);
            btn_tolak_request_pembayaran = itemView.findViewById(R.id.btn_tolak_request_pembayaran);


        }
    }
}

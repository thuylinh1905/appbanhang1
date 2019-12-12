package com.example.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.R;
import com.example.shoponline.interfac.ClickListener;
import com.example.shoponline.modelgson.SanphamNew;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class sanphamnewAdapter extends RecyclerView.Adapter<sanphamnewAdapter.ViewHolder> {
    ArrayList<SanphamNew> sanphamMDS;
    private Context context;
    private ClickListener clickListener;


    public sanphamnewAdapter(ArrayList<SanphamNew> sanphamMDS, Context context, ClickListener clickListener) {
        this.sanphamMDS = sanphamMDS;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public sanphamnewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemnew, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sanphamnewAdapter.ViewHolder holder, final int position) {
        SanphamNew sanphamMD = sanphamMDS.get(position);
        holder.TenSp.setText(sanphamMD.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.Giasp.setText("Giá : " + decimalFormat.format(sanphamMD.getGiasp()) + "VND");

        Picasso.with(context).load(sanphamMD.getHinhanh())
                .error(R.drawable.eror)
                .placeholder(R.drawable.noimage)
                .into(holder.imgHinhsp);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanphamMDS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhsp;
        TextView TenSp, Giasp, soluong;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhsp = itemView.findViewById(R.id.imgSanpham);
            TenSp = itemView.findViewById(R.id.txtTensp);
            Giasp = itemView.findViewById(R.id.txtGiassp);
            cardView = itemView.findViewById(R.id.Cv_spnews);
            soluong = itemView.findViewById(R.id.txt_viewslnew);
        }
    }
}

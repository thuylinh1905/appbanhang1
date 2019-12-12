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
import com.example.shoponline.modelgson.SptheoDM;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class itemDetailAdapter extends RecyclerView.Adapter<itemDetailAdapter.ViewHoldel> {
    ArrayList<SptheoDM> mangsp;
    Context context;
    ClickListener clickListener;

    public itemDetailAdapter(ArrayList<SptheoDM> mangsp, Context context, ClickListener clickListener) {
        this.mangsp = mangsp;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public itemDetailAdapter.ViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemDetailAdapter.ViewHoldel holder, final int position) {
        SptheoDM sanphamMD = mangsp.get(position);
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
        return mangsp.size();
    }

    public void adData(List<SptheoDM> sptheoDMS) {
        for (SptheoDM sptheoDM : sptheoDMS) {
            mangsp.add(sptheoDM);
        }
        notifyDataSetChanged();
    }

    public class ViewHoldel extends RecyclerView.ViewHolder {
        ImageView imgHinhsp;
        TextView TenSp, Giasp, soluong;
        CardView cardView;

        public ViewHoldel(@NonNull View itemView) {
            super(itemView);
            imgHinhsp = itemView.findViewById(R.id.imgSanphams);
            TenSp = itemView.findViewById(R.id.txtTensps);
            Giasp = itemView.findViewById(R.id.txtGiassps);
            cardView = itemView.findViewById(R.id.Cv_spnew);
            soluong = itemView.findViewById(R.id.txt_viewsl);
        }
    }
}

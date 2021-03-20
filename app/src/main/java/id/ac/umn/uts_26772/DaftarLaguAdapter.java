package id.ac.umn.uts_26772;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class DaftarLaguAdapter extends RecyclerView.Adapter<DaftarLaguAdapter.ItemLaguViewHolder> {
    private LinkedList<SumberLagu> mDaftarLagu;
    private LayoutInflater mInflater;
    private Context mContext;

    public DaftarLaguAdapter(Context context, LinkedList<SumberLagu> daftarLagu){
        this.mContext = context;
        this.mDaftarLagu = daftarLagu;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemLaguViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lagu_item_layout , parent , false);
        return new ItemLaguViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLaguViewHolder holder, int position) {
        SumberLagu mSumberLagu = mDaftarLagu.get(position);
        holder.tvJudul.setText(mSumberLagu.getJudul());
        holder.tvKeterangan.setText(mSumberLagu.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mDaftarLagu.size();
    }

    public class ItemLaguViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvJudul;
        private TextView tvKeterangan;
        private DaftarLaguAdapter mAdapter;
        private int mPosisi;
        private SumberLagu mSumberLagu;

        public ItemLaguViewHolder(@NonNull View itemView, DaftarLaguAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            tvKeterangan = (TextView) itemView.findViewById(R.id.tvKeterangan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSumberLagu = mDaftarLagu.get(mPosisi);
            Intent detilInten = new Intent(mContext, DetilLaguActivity.class);
            Bundle bundle = new Bundle();
            //bundle.putSerializable("DetilLagu", mSumberLagu);
            bundle.putSerializable("UrutanLagu", mPosisi);
            bundle.putSerializable("ListLagu", mDaftarLagu);
            detilInten.putExtras(bundle);
            mContext.startActivity(detilInten);
        }
    }
}

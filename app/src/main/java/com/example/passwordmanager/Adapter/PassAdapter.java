package com.example.passwordmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.passwordmanager.Database.Pass;
import com.example.passwordmanager.PassViewActivity;
import com.example.passwordmanager.R;

import java.util.List;

public class PassAdapter extends RecyclerView.Adapter<PassAdapter.PassViewHolder>{

    private Context mCtx;
    private List<Pass> passList;

    public PassAdapter(Context mCtx, List<Pass> passList) {
        this.mCtx = mCtx;
        this.passList = passList;
    }

    @Override
    public PassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_item, parent, false);
        return new PassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PassViewHolder holder, int position) {
        Pass p = passList.get(position);
        holder.textViewName.setText(p.getWebsite());
        holder.textViewEmail.setText(p.getEmail());
    }

    @Override
    public int getItemCount() {
        return passList.size();
    }

    class PassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewEmail;

        public PassViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.txtViewName);
            textViewEmail = itemView.findViewById(R.id.txtViewEmail);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Pass pass = passList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, PassViewActivity.class);
            intent.putExtra("pass", pass);

            mCtx.startActivity(intent);
        }
    }
}
package com.example.reconchainapp.ui.home.DL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reconchainapp.R;


import java.util.ArrayList;

public class distributorListAdapter extends RecyclerView.Adapter<distributorListAdapter.ViewHolder> {

    ArrayList<distributorListRequestData> mainModels;
    Context context;



    public distributorListAdapter(Context context, ArrayList<distributorListRequestData> mainModels){
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_distributor_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_name.setText(mainModels.get(position).getName());
        holder.tv_location.setText(mainModels.get(position).getLocation());
        holder.tv_email.setText(mainModels.get(position).getEmail());
    }


    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_name, tv_location,tv_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.row_dl_tv_name);
            tv_location = itemView.findViewById(R.id.row_dl_tv_location);
            tv_email = itemView.findViewById(R.id.row_dl_tv_email);
        }
    }
}


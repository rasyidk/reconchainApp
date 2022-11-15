package com.example.reconchainapp.ui.home.DR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.reconchainapp.R;
import com.example.reconchainapp.api.retrofitapi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class distributorRequestAdapter extends RecyclerView.Adapter<distributorRequestAdapter.ViewHolder> {

    ArrayList<distributorRequestData> mainModels;
    Context context;



    public distributorRequestAdapter(Context context, ArrayList<distributorRequestData> mainModels){
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_distributor_request, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String auth = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1rcnMxMjMiLCJwYXNzd29yZCI6IjEyMyIsImNvbXBhbnlfY29kZSI6ImQ4NzIyNSIsInJvbGUiOiJwcm9kdWNlciIsImVtYWlsIjoiZW1haWxAbWFpbC5jb20iLCJpYXQiOjE2NjgzMDE0MDV9.D_-O7vcEteyryD8dLMXt_0g1E7K4p4iFVKayFl95nuw";
        holder.tv_name.setText(mainModels.get(position).getName());
        holder.tv_location.setText(mainModels.get(position).getLocation());
        holder.tv_email.setText(mainModels.get(position).getEmail());

        holder.bt_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                distributorRequestInterface api = retrofitapi.getClient(view.getContext()).create(distributorRequestInterface.class);
                Call<distributorRequestDeleteResponse> call = api.confirmDistributor("Bearer "+ auth, String.valueOf(mainModels.get(position).getId()));

                call.enqueue(new Callback<distributorRequestDeleteResponse>() {
                    @Override
                    public void onResponse(Call<distributorRequestDeleteResponse> call, Response<distributorRequestDeleteResponse> response) {
                        if (response.code() == 200){
                            Toast.makeText(view.getContext(), "Accepted!", Toast.LENGTH_SHORT).show();
                            mainModels.remove(position);
                            notifyItemRemoved(position);
                            //this line below gives you the animation and also updates the
                            //list items after the deleted item
                            notifyItemRangeChanged(position, getItemCount());

                        }
                        else {
                            Toast.makeText(view.getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<distributorRequestDeleteResponse> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_name, tv_location,tv_email;
        Button bt_acc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bt_acc = itemView.findViewById(R.id.bt_acc);
            tv_name = itemView.findViewById(R.id.row_dr_tv_name);
            tv_location = itemView.findViewById(R.id.row_dr_tv_location);
            tv_email = itemView.findViewById(R.id.row_dr_tv_email);
        }
    }
}

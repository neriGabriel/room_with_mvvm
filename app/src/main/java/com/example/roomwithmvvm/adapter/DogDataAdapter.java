package com.example.roomwithmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.model.Dog;

import java.util.List;

public class DogDataAdapter extends RecyclerView.Adapter<DogDataAdapter.ViewHolder> {

    private List<Dog> dogList;


    public DogDataAdapter(List<Dog> dogList) {
        this.dogList = dogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_data_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(this.dogList.get(position).getName());
        holder.textId.setText(String.valueOf(this.dogList.get(position).getId()));
        holder.textOrigin.setText( this.dogList.get(position).getOrigin());
    }

    @Override
    public int getItemCount() {
        return this.dogList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textId;
        TextView textOrigin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textName = itemView.findViewById(R.id.textName);
            this.textId   = itemView.findViewById(R.id.textId);
            this.textOrigin = itemView.findViewById(R.id.textOrigin);
        }
    }
}

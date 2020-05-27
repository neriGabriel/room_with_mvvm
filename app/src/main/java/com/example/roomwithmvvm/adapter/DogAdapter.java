package com.example.roomwithmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.view.DogListFragmentDirections;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private List<Dog> dogList;

    public DogAdapter(List<Dog> dogList) {
        this.dogList = dogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textName.setText(this.dogList.get(position).getName());
            holder.textLife.setText(this.dogList.get(position).getLife_span());
            holder.textName.setOnClickListener(view -> {
                NavDirections action = DogListFragmentDirections.actionDogListFragmentToDogDetailsFragment(this.dogList.get(position));
                Navigation.findNavController(view).navigate(action);
            });
    }

    @Override
    public int getItemCount() {
        return this.dogList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textLife;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textName = itemView.findViewById(R.id.textName);
            this.textLife = itemView.findViewById(R.id.textLife);
        }
    }
}

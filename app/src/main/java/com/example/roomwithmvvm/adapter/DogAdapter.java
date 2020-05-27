package com.example.roomwithmvvm.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.database.DatabaseConnection;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.view.DogListFragmentDirections;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private List<Dog> dogList;
    private DatabaseConnection connection;


    public DogAdapter(List<Dog> dogList) {
        this.dogList = dogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);
        this.connection = DatabaseConnection.getInstance(parent.getContext());

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textName.setText(this.dogList.get(position).getName());
            holder.textId.setText(String.valueOf(this.dogList.get(position).getId()));
            holder.textOrigin.setText( this.dogList.get(position).getOrigin());
            holder.cardItem.setOnClickListener(view -> {
                NavDirections action = DogListFragmentDirections.actionDogListFragmentToDogDetailsFragment(this.dogList.get(position));
                Navigation.findNavController(view).navigate(action);
            });
            holder.downloadDog.setOnClickListener( view -> {
                Dog dog = connection.dogDao().getById(this.dogList.get(position).getId());
                if(dog != null) {
                    Toast.makeText(holder.cardItem.getContext(), "Dog já existente na base de dados! ", Toast.LENGTH_SHORT).show();
                } else {
                    connection.dogDao().insert(this.dogList.get(position));
                    Toast.makeText(holder.cardItem.getContext(), "Dog vinculado a base de dados local! ", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return this.dogList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textId;
        TextView textOrigin;
        ImageView downloadDog;
        CardView cardItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textName = itemView.findViewById(R.id.textName);
            this.textId   = itemView.findViewById(R.id.textId);
            this.textOrigin = itemView.findViewById(R.id.textOrigin);
            this.downloadDog = itemView.findViewById(R.id.imageDownload);
            this.cardItem = itemView.findViewById(R.id.cardItem);
        }
    }
}

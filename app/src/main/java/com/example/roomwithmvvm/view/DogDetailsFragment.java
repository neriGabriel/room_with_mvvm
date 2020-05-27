package com.example.roomwithmvvm.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.roomwithmvvm.databinding.FragmentDogDetailsBinding;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.model.Imagem;
import com.example.roomwithmvvm.viewmodel.DogDetailsViewModel;

import java.io.IOException;
import java.io.InputStream;


public class DogDetailsFragment extends Fragment {

    private FragmentDogDetailsBinding binding;
    private Dog dog;
    private Imagem imagem;
    private ActionBar actionBar;
    private DogDetailsViewModel dogDetailsViewModel;
    public DogDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDogDetailsBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("Dog details");
        this.dogDetailsViewModel = new ViewModelProvider(this).get(DogDetailsViewModel.class);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {
            this.dog = DogDetailsFragmentArgs.fromBundle(getArguments()).getDog();
            this.binding.textName.setText(this.dog.getName());
            this.binding.textCategory.setText(this.dog.getBreed_group());
            this.binding.textLifeSpan.setText(this.dog.getLife_span());

            this.dogDetailsViewModel.getImagem(this.dog.getId()).observe(getViewLifecycleOwner(), s -> {
                if(s != null) {
                    imagem = s.get(0);
                    Glide.with(view)
                            .load(imagem.getUrl())
                            .into(this.binding.imageDog);
                }
            });
        }
    }
}

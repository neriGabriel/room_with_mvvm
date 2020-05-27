package com.example.roomwithmvvm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomwithmvvm.databinding.FragmentDogDetailsBinding;
import com.example.roomwithmvvm.model.Dog;


public class DogDetailsFragment extends Fragment {

    private FragmentDogDetailsBinding binding;
    private Dog dog;
    private ActionBar actionBar;
    public DogDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDogDetailsBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("Dog details");

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
        }
    }
}

package com.example.roomwithmvvm.view;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomwithmvvm.adapter.DogDataAdapter;
import com.example.roomwithmvvm.databinding.FragmentDogDataBinding;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.viewmodel.DogDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class DogDataFragment extends Fragment {

    private FragmentDogDataBinding binding;
    private DogDataAdapter dogAdapter;
    private List<Dog> dogList = new ArrayList<>();
    private DogDataViewModel dogDataViewModel;
    private ActionBar actionBar;

    public DogDataFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDogDataBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("Dog stored in device");

        this.dogAdapter = new DogDataAdapter(this.dogList);
        this.dogList.clear();

        this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.recyclerView.setAdapter(this.dogAdapter);

        this.dogDataViewModel = new ViewModelProvider(this).get(DogDataViewModel.class);

        this.dogDataViewModel.getAllDogs().observe(getViewLifecycleOwner(), s -> {
            if(s != null) {
                this.dogList.addAll(s);
                this.dogAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }
}

package com.example.roomwithmvvm.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.adapter.DogAdapter;
import com.example.roomwithmvvm.databinding.FragmentDogListBinding;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.retrofit.RetrofitConfig;
import com.example.roomwithmvvm.viewmodel.DogViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DogListFragment extends Fragment implements LifecycleOwner {

    private FragmentDogListBinding binding;
    private DogAdapter dogAdapter;
    private List<Dog> dogList = new ArrayList<>();
    private DogViewModel dogViewModel;


    public DogListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDogListBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        this.dogAdapter = new DogAdapter(this.dogList);
        this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.recyclerView.setAdapter(this.dogAdapter);

        this.dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);


        this.dogViewModel.getAllDogs().observe(getViewLifecycleOwner(), s -> {
            if(s != null) {
                this.dogList.addAll(s);
                this.dogAdapter.notifyDataSetChanged();
            }
        });

        this.binding.btnSincronizar.setOnClickListener(view ->
            dogViewModel.getDogById(1).observe(getViewLifecycleOwner(), s -> {
            if(s != null) {
                dogList.clear();
                dogList.add(s);
                dogAdapter.notifyDataSetChanged();
            }
        }));


        return v;
    }
}

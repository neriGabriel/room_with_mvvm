package com.example.roomwithmvvm.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DogListFragment extends Fragment {

    private FragmentDogListBinding binding;
    private DogAdapter dogAdapter;
    private List<Dog> dogList = new ArrayList<>();
    private RetrofitConfig retrofitConfig;
    private Call<List<Dog>> request;

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

        this.retrofitConfig = new RetrofitConfig();

        this.request = retrofitConfig.getDogAPI().getAllDogs();
        this.request.enqueue(new Callback<List<Dog>>() {

            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                Log.d("DogListFragment", response.body().toString());
                dogList.addAll(response.body());
                dogAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                Log.d("DogListFragment", "deu ruim!");
            }
        });

        return v;
    }
}

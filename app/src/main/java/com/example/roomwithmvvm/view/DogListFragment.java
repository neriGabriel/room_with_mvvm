package com.example.roomwithmvvm.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.adapter.DogAdapter;
import com.example.roomwithmvvm.databinding.FragmentDogListBinding;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.viewmodel.DogListViewModel;

import java.util.ArrayList;
import java.util.List;

public class DogListFragment extends Fragment implements LifecycleOwner {

    private FragmentDogListBinding binding;
    private DogAdapter dogAdapter;
    private List<Dog> dogList = new ArrayList<>();
    private DogListViewModel dogListViewModel;
    private ActionBar actionBar;

    public DogListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDogListBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        setHasOptionsMenu(true);
        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("Dog list");

        this.dogAdapter = new DogAdapter(this.dogList);
        this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.recyclerView.setAdapter(this.dogAdapter);

        this.dogListViewModel = new ViewModelProvider(this).get(DogListViewModel.class);

        this.dogList.clear();
        this.getAllDogs();

        this.binding.btnSincronizar.setOnClickListener(view -> {
            this.dogList.clear();
            if(this.binding.edtId.getText().toString().equals("")) {
                this.getAllDogs();
            }
            else {
                 this.getDogById(Integer.parseInt(this.binding.edtId.getText().toString()));
            }
            binding.edtId.setText("");
        });

        return v;
    }

    private void getAllDogs() {
        this.dogListViewModel.getAllDogs().observe(getViewLifecycleOwner(), s -> {
            if(s != null) {
                this.dogList.addAll(s);
                this.dogAdapter.notifyDataSetChanged();
                this.closeKeyBoard(getView(), getContext());
                this.binding.btnSincronizar.setEnabled(true);
            } else {
                Toast.makeText(getContext(), "Não foi possível conectar a API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDogById(int id) {
        dogListViewModel.getDogById(id).observe(getViewLifecycleOwner(), s -> {
            if (s.getId() != null) {
                dogList.add(s);
                dogAdapter.notifyDataSetChanged();
                this.closeKeyBoard(getView(), getContext());
            } else {
                Toast.makeText(getActivity(), "Nenhum cachorro foi econtrado o id informado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void closeKeyBoard(@NonNull View view, @NonNull Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.dadosCel:
                NavDirections action = DogListFragmentDirections.actionDogListFragmentToDogDataFragment();
                Navigation.findNavController(getView()).navigate(action);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}

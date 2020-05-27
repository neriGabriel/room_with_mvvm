package com.example.roomwithmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomwithmvvm.database.DatabaseConnection;
import com.example.roomwithmvvm.model.Dog;

import java.util.List;

public class DogDataViewModel extends AndroidViewModel {

    private DatabaseConnection connection;
    private MutableLiveData<List<Dog>> dogList = new MutableLiveData<>();

    public DogDataViewModel(@NonNull Application application) {
        super(application);
        this.connection = DatabaseConnection.getInstance(application);
    }

    public MutableLiveData<List<Dog>> getAllDogs() {
        if(this.dogList.getValue() == null) {
            dogList.setValue(connection.dogDao().getAllDogs());
        }

        return this.dogList;
    }
}

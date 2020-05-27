package com.example.roomwithmvvm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomwithmvvm.model.Dog;

import java.util.List;

@Dao
public interface DogDao {

    @Insert
    void insert(Dog dog);

    @Query("SELECT * FROM tb_dog")
    List<Dog> getAllDogs();

    @Query("SELECT * FROM tb_dog WHERE dog_id = :id")
    Dog getById(int id);

    @Delete
    void delete(Dog dog);
}

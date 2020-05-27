package com.example.roomwithmvvm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomwithmvvm.model.Dog;

@Database(entities = {Dog.class}, version = 1, exportSchema = false)
public abstract class DatabaseConnection extends RoomDatabase {

    private static DatabaseConnection instance;

    public abstract DogDao dogDao();

    public static DatabaseConnection getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, DatabaseConnection.class, "dog_database")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}

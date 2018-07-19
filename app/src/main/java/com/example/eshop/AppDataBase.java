package com.example.eshop;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Order.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract AppDao appDao();
}

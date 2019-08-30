package com.yugorsk.school6.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yugorsk.school6.data.Date;

import java.util.List;

@Dao
public interface DateDao {

    @Query("SELECT * FROM Date")
    LiveData<List<Date>> getAllDate();

    @Query("SELECT * FROM Date WHERE id = :id")
    LiveData<Date> getDateById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Date> dates);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Date date);

    @Update
    void update(Date date);

    @Delete
    void delete(Date date);
}

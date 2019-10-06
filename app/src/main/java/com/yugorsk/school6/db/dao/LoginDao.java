package com.yugorsk.school6.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;

import java.util.List;

@Dao
public interface LoginDao {

    @Query("SELECT * FROM Login")
    LiveData<List<Login>> getLogin();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login login);

    @Update
    void update(Login login);

    @Delete
    void delete(Login login);

    @Query("DELETE FROM Login")
    void deleteAll();
}

package com.yugorsk.school6.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM News")
    LiveData<List<News>> getAllNews();

    @Query("SELECT * FROM News WHERE id = :id")
    LiveData<News> getNewsById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<News> news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Update
    void update(News news);

    @Delete
    void delete(News news);

    @Query("DELETE FROM News")
    void deleteAll();
}

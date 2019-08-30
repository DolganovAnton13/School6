package com.yugorsk.school6.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yugorsk.school6.data.News;
import com.yugorsk.school6.data.Schedule;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Query("SELECT * FROM Schedule")
    LiveData<List<Schedule>> getAllSchedule();

    @Query("SELECT * FROM Schedule WHERE id = :id")
    LiveData<Schedule> getScheduleById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Schedule> schedules);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Schedule schedule);

    @Update
    void update(Schedule schedule);

    @Delete
    void delete(Schedule schedule);
}

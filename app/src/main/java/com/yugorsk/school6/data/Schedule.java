package com.yugorsk.school6.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private int indexPicture;

    public Schedule(int indexPicture) {
        this.indexPicture = indexPicture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIndexPicture() {
        return indexPicture;
    }

    public void setIndexPicture(int indexPicture) {
        this.indexPicture = indexPicture;
    }
}

package com.yugorsk.school6.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Schedule {

    @NonNull
    @PrimaryKey
    private String id;

    private int indexPicture;

    public Schedule(int indexPicture) {
        this.indexPicture = indexPicture;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getIndexPicture() {
        return indexPicture;
    }

    public void setIndexPicture(int indexPicture) {
        this.indexPicture = indexPicture;
    }
}

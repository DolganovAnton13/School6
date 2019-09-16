package com.yugorsk.school6.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Date {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String quarterOneFrom;      //первая четверть с
    private String quarterOneTo;        //первая четверть до
    private String quarterTwoFrom;      //вторая четверть с
    private String quarterTwoTo;        //вторая четверть до
    private String quarterThreeFrom;    //третья четверть с
    private String quarterThreeTo;      //третья четверть до
    private String quarterFourFrom;     //четвертая четверть с
    private String quarterFourTo;       //четвертая четверть до

    public Date(String quarterOneFrom, String quarterOneTo, String quarterTwoFrom, String quarterTwoTo, String quarterThreeFrom, String quarterThreeTo, String quarterFourFrom, String quarterFourTo) {
        this.quarterOneFrom = quarterOneFrom;
        this.quarterOneTo = quarterOneTo;
        this.quarterTwoFrom = quarterTwoFrom;
        this.quarterTwoTo = quarterTwoTo;
        this.quarterThreeFrom = quarterThreeFrom;
        this.quarterThreeTo = quarterThreeTo;
        this.quarterFourFrom = quarterFourFrom;
        this.quarterFourTo = quarterFourTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuarterOneFrom() {
        return quarterOneFrom;
    }

    public String getQuarterOneTo() {
        return quarterOneTo;
    }

    public String getQuarterTwoFrom() {
        return quarterTwoFrom;
    }

    public String getQuarterTwoTo() {
        return quarterTwoTo;
    }

    public String getQuarterThreeFrom() {
        return quarterThreeFrom;
    }

    public String getQuarterThreeTo() {
        return quarterThreeTo;
    }

    public String getQuarterFourFrom() {
        return quarterFourFrom;
    }

    public String getQuarterFourTo() {
        return quarterFourTo;
    }

    public void setQuarterOneFrom(String quarterOneFrom) {
        this.quarterOneFrom = quarterOneFrom;
    }

    public void setQuarterOneTo(String quarterOneTo) {
        this.quarterOneTo = quarterOneTo;
    }

    public void setQuarterTwoFrom(String quarterTwoFrom) {
        this.quarterTwoFrom = quarterTwoFrom;
    }

    public void setQuarterTwoTo(String quarterTwoTo) {
        this.quarterTwoTo = quarterTwoTo;
    }

    public void setQuarterThreeFrom(String quarterThreeFrom) {
        this.quarterThreeFrom = quarterThreeFrom;
    }

    public void setQuarterThreeTo(String quarterThreeTo) {
        this.quarterThreeTo = quarterThreeTo;
    }

    public void setQuarterFourFrom(String quarterFourFrom) {
        this.quarterFourFrom = quarterFourFrom;
    }

    public void setQuarterFourTo(String quarterFourTo) {
        this.quarterFourTo = quarterFourTo;
    }
}

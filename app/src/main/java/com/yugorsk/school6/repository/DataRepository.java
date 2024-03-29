package com.yugorsk.school6.repository;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.yugorsk.school6.callback.ProgressDialogCallback;
import com.yugorsk.school6.callback.SnackbarCallback;
import com.yugorsk.school6.data.Call;
import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.network.FirebaseQueryLiveData;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataRepository {

    private static final DatabaseReference Date_REF = FirebaseDatabase.getInstance().getReference().child("Даты");
    private static final DatabaseReference Call_REF = FirebaseDatabase.getInstance().getReference().child("Звонки");
    private static final DatabaseReference Login_REF = FirebaseDatabase.getInstance().getReference().child("Администрирование");
    private static final DatabaseReference News_REF = FirebaseDatabase.getInstance().getReference().child("Новости");


    MutableLiveData<StorageReference> storageLiveData;

    public DataRepository() {
        storageLiveData = new MutableLiveData<>();
    }

    @NonNull
    public LiveData<Date> getDateLiveData() {
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Date_REF);
        LiveData<Date> dateLiveData = Transformations.map(liveData, new DataSnapshotDate());
        return dateLiveData;
    }

    @NonNull
    public LiveData<Call> getCallLiveData() {
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Call_REF);
        LiveData<Call> callLiveData = Transformations.map(liveData, new DataSnapshotCall());
        return callLiveData;
    }

    @NonNull
    public LiveData<List<Login>> getLoginLiveData() {
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Login_REF);
        LiveData<List<Login>> loginLiveData = Transformations.map(liveData, new DataSnapshotLogin());
        return loginLiveData;
    }

    @NonNull
    public LiveData<List<News>> getNewsLiveData() {
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(News_REF);
        LiveData<List<News>> newsLiveData = Transformations.map(liveData, new DataSnapshotNews());
        return newsLiveData;
    }

    public LiveData<StorageReference> getScheduleLiveData(int id) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = null;
        switch (id) {
            case 0:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/1.jpg");
                break;
            case 1:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/2.jpg");
                break;
            case 2:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/3.jpg");
                break;
            case 3:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/4.jpg");
                break;
            case 4:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/11.jpg");
                break;
        }

        storageLiveData.setValue(storageRef);
        return storageLiveData;
    }

    private class DataSnapshotDate implements Function<DataSnapshot, Date> {
        @Override
        public Date apply(DataSnapshot dataSnapshot) {

            Date date = new Date();
            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                switch (dsp.getKey()) {
                    case "1-ая четверть до":
                        date.setQuarterOneTo(dsp.getValue().toString());
                        break;
                    case "1-ая четверть с":
                        date.setQuarterOneFrom(dsp.getValue().toString());
                        break;
                    case "2-ая четверть до":
                        date.setQuarterTwoTo(dsp.getValue().toString());
                        break;
                    case "2-ая четверть с":
                        date.setQuarterTwoFrom(dsp.getValue().toString());
                        break;
                    case "3-я четверть до":
                        date.setQuarterThreeTo(dsp.getValue().toString());
                        break;
                    case "3-я четверть с":
                        date.setQuarterThreeFrom(dsp.getValue().toString());
                        break;
                    case "4-ая четверть до":
                        date.setQuarterFourTo(dsp.getValue().toString());
                        break;
                    case "4-ая четверть с":
                        date.setQuarterFourFrom(dsp.getValue().toString());
                        break;
                }
            }
            return date;
        }
    }

    private class DataSnapshotCall implements Function<DataSnapshot, Call> {
        @Override
        public Call apply(DataSnapshot dataSnapshot) {
            Call call = new Call();

            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                switch (dsp.getKey().toString()) {
                    case "1_1":
                        call.setLesson1_1(dsp.getValue().toString());
                        break;
                    case "1_2":
                        call.setLesson1_2(dsp.getValue().toString());
                        break;
                    case "1_3":
                        call.setLesson1_3(dsp.getValue().toString());
                        break;
                    case "1_4":
                        call.setLesson1_4(dsp.getValue().toString());
                        break;
                    case "1_5":
                        call.setLesson1_5(dsp.getValue().toString());
                        break;
                    case "1_6":
                        call.setLesson1_6(dsp.getValue().toString());
                        break;
                    case "2_1":
                        call.setLesson2_1(dsp.getValue().toString());
                        break;
                    case "2_2":
                        call.setLesson2_2(dsp.getValue().toString());
                        break;
                    case "2_3":
                        call.setLesson2_3(dsp.getValue().toString());
                        break;
                    case "2_4":
                        call.setLesson2_4(dsp.getValue().toString());
                        break;
                    case "2_5":
                        call.setLesson2_5(dsp.getValue().toString());
                        break;
                    case "дата":
                        call.setDateText(dsp.getValue().toString());
                        break;
                }
            }
            return call;
        }
    }

    private class DataSnapshotLogin implements Function<DataSnapshot, List<Login>> {
        @Override
        public List<Login> apply(DataSnapshot dataSnapshot) {
            List<Login> listLogin = new ArrayList<>();

            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                listLogin.add(new Login(dsp.getKey(), dsp.getValue().toString()));
            }
            return listLogin;
        }
    }

    private class DataSnapshotNews implements Function<DataSnapshot, List<News>> {
        @Override
        public List<News> apply(DataSnapshot dataSnapshot) {
            List<News> listNews = new ArrayList<>();
            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                listNews.add(new News(dsp.getValue().toString(), dsp.getKey()));
            }
            Collections.reverse(listNews);
            return listNews;
        }
    }

    public void LoadPicture(Uri filePath, String number, ProgressDialogCallback progressCallback, SnackbarCallback snackbarCallback) {

        progressCallback.ProgressDialogShow();
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("расписание уроков/" + number);
        ref.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressCallback.ProgressDialogDissmiss();
                        snackbarCallback.SnackbarShow("Загружено");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressCallback.ProgressDialogDissmiss();
                        snackbarCallback.SnackbarShow("Ошибка загрузки");
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressCallback.ProgressDialogSetMessage("Загрузка " + (int) progress + "%");
                    }
                });

    }

    public void LoadNews(String key, String news, SnackbarCallback snackbarCallback) {

        News_REF.child(key).setValue(news).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                snackbarCallback.SnackbarShow("Загружено");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                snackbarCallback.SnackbarShow("Ошибка загрузки");
            }
        });
    }

    public void DeleteNews(String key, SnackbarCallback snackbarCallback) {

        News_REF.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                snackbarCallback.SnackbarShow("Удалено");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                snackbarCallback.SnackbarShow("Ошибка удаления");
            }
        });
    }
}

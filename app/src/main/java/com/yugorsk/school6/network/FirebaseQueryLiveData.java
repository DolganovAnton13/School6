package com.yugorsk.school6.network;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.*;

public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {

    private final Query query;
    private final ValueEventListener listener = new MyValueEventListener();

    private boolean listenerRemovePending = false;
    private final Handler handler = new Handler();

    private final Runnable removeListener = new Runnable() {
        @Override
        public void run() {
            query.removeEventListener(listener);
            listenerRemovePending = false;
        }
    };

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    public FirebaseQueryLiveData(DatabaseReference reference) {
        this.query = reference;
    }

    @Override
    protected void onActive() {
        if(listenerRemovePending){
            handler.removeCallbacks(removeListener);
        } else {
            query.addValueEventListener(listener);
        }
        listenerRemovePending = false;
    }

    @Override
    protected void onInactive() {
        handler.postDelayed(removeListener, 2000);
        listenerRemovePending = true;
    }

    private class MyValueEventListener implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e("TAG",  "Cannot listen to query " + query, databaseError.toException());
        }
    }
}
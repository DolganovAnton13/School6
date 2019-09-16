package com.yugorsk.school6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    ActivityMainBinding binding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private MainViewModel model;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        model = ViewModelProviders.of(this).get(MainViewModel.class);

        //если есть интернет, то
         //updateDateList();
         //иначе
        loadDateList();
    }

    private void updateDateList()
    {
        model.getDateFromServer().observe(this,dates ->{

            CalendarDate calendarDate = new CalendarDate(dates);
            model.updateDate(calendarDate.toDate());
        });
    }


    private void loadDateList()
    {
        LiveData<List<Date>> date = model.getDate();
        date.observe(this,dates -> {

            CalendarDate calendarDate = new CalendarDate(dates.get(0));
            binding.newTextForDate.setText(calendarDate.CurrentDate());

        });
    }
}

package com.yugorsk.school6.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.os.Bundle;

import android.view.Gravity;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import com.yugorsk.school6.view.fragment.FragmentAboutApp;
import com.yugorsk.school6.view.fragment.FragmentAboutSchoolMain;
import com.yugorsk.school6.view.fragment.FragmentCallScheduleMain;
import com.yugorsk.school6.view.fragment.FragmentContactsMain;
import com.yugorsk.school6.view.fragment.FragmentLogin;
import com.yugorsk.school6.view.fragment.FragmentMain;
import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.ActivityMainBinding;
import com.yugorsk.school6.view.fragment.FragmentNews;
import com.yugorsk.school6.view.fragment.FragmentSchedule;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavController navController;
    ActivityMainBinding binding;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        navigationView = findViewById(R.id.navigationView);
        //navController = Navigation.findNavController(this, R.id.hostFragment);

        //NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.hostFragment.getId(), new FragmentMain());
            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;
        menuItem.setChecked(true);

        binding.drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.Main:
                //navController.navigate(R.id.Main);
                selectedFragment = new FragmentMain();
                break;
            case R.id.AboutSchool:
                //navController.navigate(R.id.AboutSchool);
                selectedFragment = new FragmentAboutSchoolMain();
                break;
            case R.id.Schedule:
                selectedFragment=new FragmentSchedule();
                break;
            case R.id.Call:
                selectedFragment=new FragmentCallScheduleMain();
                break;
            case R.id.News:
                selectedFragment=new FragmentNews();
                break;
            case R.id.Contacts:
                selectedFragment=new FragmentContactsMain();
                break;
            case R.id.Admin:
                selectedFragment=new FragmentLogin();
                break;
            case R.id.AboutApp:
                selectedFragment=new FragmentAboutApp();
                break;
            default:
                selectedFragment = new FragmentMain();
                break;

        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.hostFragment.getId(), selectedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        return true;
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.hostFragment.getId(), fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void setToolbar(Toolbar toolbar, String title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, binding.drawerLayout, toolbar, R.string.main, R.string.close);

            binding.drawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        } else {
            binding.drawerLayout.setDrawerListener(null);
        }
    }

    @Override
    public void onBackPressed() {

        if (binding.drawerLayout != null && binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        else {

            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0)
                super.onBackPressed();
            else
                getSupportFragmentManager().popBackStack();
        }
    }

}

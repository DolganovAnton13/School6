package com.yugorsk.school6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import com.yugorsk.school6.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavController navController;
    ActivityMainBinding binding;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.toolbar.setTitle("Главная");
        setSupportActionBar(binding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.main, R.string.close);

        toggle.syncState();
        binding.drawerLayout.addDrawerListener(toggle);
        navigationView = findViewById(R.id.navigationView);
        navigationView.getMenu().getItem(0).setChecked(true);
        //navController = Navigation.findNavController(this, R.id.hostFragment);

        //NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.hostFragment.getId(), new FragmentMain());
        fragmentTransaction.commit();
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
                navigationView.getMenu().getItem(0).setChecked(true);
                binding.toolbar.setTitle("Главная");
                selectedFragment = new FragmentMain();
                break;

            case R.id.AboutSchool:
                //navController.navigate(R.id.AboutSchool);
                navigationView.getMenu().getItem(1).setChecked(true);
                binding.toolbar.setTitle("О школе");
                selectedFragment = new FragmentAboutSchool();
                break;

            default:
                navigationView.getMenu().getItem(0).setChecked(true);
                binding.toolbar.setTitle("Главная");
                selectedFragment = new FragmentMain();
                break;

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.replace(binding.hostFragment.getId(), selectedFragment);
        fragmentTransaction.commit();
        return true;
    }
}

package com.yugorsk.school6.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.yugorsk.school6.R;
import com.yugorsk.school6.adapter.ViewPagerAdapterShowPhoto;

public class ShowPhoto extends AppCompatActivity {

    ViewPager viewPager;
    Toolbar toolbar;
    int position;
    int numberFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        toolbar = (Toolbar) findViewById(R.id.toolbarShowPhoto);
        toolbar.setTitle("Просмотр фотографий");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle arguments = getIntent().getExtras();
        numberFragment=arguments.getInt("fragment");
        position=arguments.getInt("position");

        viewPager = (ViewPager) findViewById(R.id.viewPagerShowPhoto);
        ViewPagerAdapterShowPhoto viewPagerAdapter = new ViewPagerAdapterShowPhoto(this,numberFragment);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
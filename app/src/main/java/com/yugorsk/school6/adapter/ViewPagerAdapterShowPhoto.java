package com.yugorsk.school6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.yugorsk.school6.R;

import uk.co.senab.photoview.PhotoView;

public class ViewPagerAdapterShowPhoto extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images1 = {R.drawable.school5, R.drawable.aboutschool1, R.drawable.aboutschool2, R.drawable.aboutschool3, R.drawable.aboutschool4, R.drawable.aboutschool5, R.drawable.aboutschool7, R.drawable.school6, R.drawable.aboutschool8, R.drawable.aboutschool9};
    private Integer[] images2 = {R.drawable.aboutschool10, R.drawable.aboutschool11};
    private Integer[] images3 = {R.drawable.aboutschool12, R.drawable.aboutschool13};
    private Integer[] images4 = {R.drawable.aboutschool14, R.drawable.aboutschool15, R.drawable.aboutschool16, R.drawable.aboutschool17};
    private int number;


    public ViewPagerAdapterShowPhoto(Context context, int numberFragment) {
        this.context = context;
        this.number = numberFragment;
    }

    @Override
    public int getCount() {
        if (number == 1) return images1.length;
        if (number == 2) return images2.length;
        if (number == 3) return images3.length;
        if (number == 4) return images4.length;

        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_show_photo, null);
        PhotoView imageView = (PhotoView) view.findViewById(R.id.imageShowPhoto);

        if (number == 1) {
            imageView.setImageResource(images1[position]);
        }
        if (number == 2) {
            imageView.setImageResource(images2[position]);
        }
        if (number == 3) {
            imageView.setImageResource(images3[position]);
        }
        if (number == 4) {
            imageView.setImageResource(images4[position]);
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}

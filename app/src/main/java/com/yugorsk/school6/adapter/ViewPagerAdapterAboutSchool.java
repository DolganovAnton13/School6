package com.yugorsk.school6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.yugorsk.school6.R;
import com.yugorsk.school6.view.ShowPhoto;

public class ViewPagerAdapterAboutSchool extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images1 = {R.drawable.school5, R.drawable.aboutschool1, R.drawable.aboutschool2, R.drawable.aboutschool3, R.drawable.aboutschool4, R.drawable.aboutschool5, R.drawable.aboutschool7, R.drawable.school6, R.drawable.aboutschool8, R.drawable.aboutschool9};
    private Integer[] images2 = {R.drawable.aboutschool10, R.drawable.aboutschool11};
    private Integer[] images3 = {R.drawable.aboutschool12, R.drawable.aboutschool13};
    private Integer[] images4 = {R.drawable.aboutschool14, R.drawable.aboutschool15, R.drawable.aboutschool16, R.drawable.aboutschool17};
    private int number;

    public ViewPagerAdapterAboutSchool(Context context, int numberFragment) {
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
        View view = layoutInflater.inflate(R.layout.custom_view_pager, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageAboutSchool);

        if (number == 1) {
            imageView.setImageResource(images1[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number == 1) {
                    Intent intent0 = new Intent(context, ShowPhoto.class);
                    intent0.putExtra("fragment", 1);
                    intent0.putExtra("position", position);
                    context.startActivity(intent0);
                }
                if (number == 2) {
                    Intent intent0 = new Intent(context, ShowPhoto.class);
                    intent0.putExtra("fragment", 2);
                    intent0.putExtra("position", position);
                    context.startActivity(intent0);
                }
                if (number == 3) {
                    Intent intent0 = new Intent(context, ShowPhoto.class);
                    intent0.putExtra("fragment", 3);
                    intent0.putExtra("position", position);
                    context.startActivity(intent0);
                }
                if (number == 4) {
                    Intent intent0 = new Intent(context, ShowPhoto.class);
                    intent0.putExtra("fragment", 4);
                    intent0.putExtra("position", position);
                    context.startActivity(intent0);
                }
            }
        });
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

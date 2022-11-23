package com.example.explorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] = {

            R.drawable.image1,
            R.drawable.image2
    };

    int headings[] = {
            R.string.heading_one,
            R.string.heading_two
    };

    int description[] = {

            R.string.desc_one,
            R.string.desc_two
    };

    public ViewPagerAdapter(Context context) {

        this.context = context;

    }

    public ViewPagerAdapter(FragmentManager supportFragmentManager) {
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.sliderimage1);
        TextView slideHeading = (TextView) view.findViewById(R.id.textdescription1);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);

    }

    public void add(ExplorechinahotelFragment explorechinahotelFragment, String s) {
    }
}
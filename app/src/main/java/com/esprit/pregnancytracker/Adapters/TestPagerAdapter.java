package com.esprit.pregnancytracker.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esprit.pregnancytracker.Models.PagerModel;

import java.util.List;
import com.esprit.pregnancytracker.R;

/**
 * Created by Asus on 10/12/2017.
 */

public class TestPagerAdapter extends PagerAdapter {
    Context context;
    List<PagerModel> pagerModelList;
    LayoutInflater inflater;

    public TestPagerAdapter(Context context, List<PagerModel> pagerModelList) {
        this.context = context;
        this.pagerModelList = pagerModelList;
        this.inflater = ((Activity)context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return pagerModelList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.resultpopup,container,false);
        TextView titleResult = (TextView)view.findViewById(R.id.titleResult);
        TextView desriptionResult = (TextView)view.findViewById(R.id.descriptionResult);
        ImageView imgResult = (ImageView)view.findViewById(R.id.imgResult);
        view.setTag(position);
        ((ViewPager)container).addView(view);
        PagerModel model = pagerModelList.get(position);
        titleResult.setText(model.getTitlePager());
        desriptionResult.setText(model.getDescriptionPager());
        int id = context.getResources().getIdentifier("drawable/", null, context.getPackageName());
        imgResult.setImageResource(R.drawable.resultpregnant);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}

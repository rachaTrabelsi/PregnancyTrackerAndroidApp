package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.NewsForDoctor;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.DetailsInformationActivity;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

public class NewsForDoctorAdapter extends ArrayAdapter<NewsForDoctor> {
    Context mCtx;
    List<NewsForDoctor> news;

    public NewsForDoctorAdapter(Context mCtx, List<NewsForDoctor> news) {
        super(mCtx, 0, 0, news);
        this.mCtx = mCtx;
        this.news = news;

    }

    public static class ViewHolder {
        ImageView imageInfo;
        TextView urlInfo;
        TextView titreInfo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        NewsForDoctor news = getItem(position);

        NewsForDoctorAdapter.ViewHolder vHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.news_for_doctor_item, parent, false);
            vHolder = new NewsForDoctorAdapter.ViewHolder();
            vHolder.urlInfo = (TextView) convertView.findViewById(R.id.title_news_for_doctor_url);
            vHolder.titreInfo = (TextView) convertView.findViewById(R.id.title_news_for_doctor_tv);
            vHolder.imageInfo = (ImageView) convertView.findViewById(R.id.news_for_doctor_img);
            convertView.setTag(vHolder);


        } else {
            vHolder = (NewsForDoctorAdapter.ViewHolder) convertView.getTag();


        }


        Picasso.with(mCtx)
                .load(PregnancyTrackerURLS.URL_IMAGES_FOR_NEWS+news.getImage())
                .into(vHolder.imageInfo);
        vHolder.urlInfo.setText("More infos : "+news.getUrl());
        vHolder.titreInfo.setText(news.getTitre());

        return convertView;
    }







}

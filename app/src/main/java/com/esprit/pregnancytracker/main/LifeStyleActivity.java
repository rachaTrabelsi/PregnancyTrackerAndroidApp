package com.esprit.pregnancytracker.main;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.ValuesClass;


public class LifeStyleActivity extends AppCompatActivity {
   ImageView menualimentation,menusleep,menuwater,menusport;
    ValuesClass vc = new ValuesClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_style);
      //
        menualimentation = (ImageView)findViewById(R.id.menu_alimentation);
        menusleep = (ImageView)findViewById(R.id.menu_sleep);
        menuwater = (ImageView)findViewById(R.id.menu_water);
        menusport = (ImageView)findViewById(R.id.menu_sport);
        menualimentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               vc.setCategorie("alimentation");
                Intent intent = new Intent(LifeStyleActivity.this, InformationActivity.class);
                intent.putExtra("categorie", vc.getCategorie());
                startActivity(intent);


            }
        });
        menusleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vc.setCategorie("sleep");
                Intent intent = new Intent(LifeStyleActivity.this, sportwatersleepActivity.class);
                intent.putExtra("categorie", vc.getCategorie());
                startActivity(intent);

            }
        });
        menuwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vc.setCategorie("water");
                Intent intent = new Intent(LifeStyleActivity.this, sportwatersleepActivity.class);
                intent.putExtra("categorie", vc.getCategorie());
                startActivity(intent);

            }
        });
        menusport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vc.setCategorie("sport");
                Intent intent = new Intent(LifeStyleActivity.this, sportwatersleepActivity.class);
                intent.putExtra("categorie", vc.getCategorie());
                startActivity(intent);

            }
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.lifestyle,fragment);
        ft.commit();
    }

}

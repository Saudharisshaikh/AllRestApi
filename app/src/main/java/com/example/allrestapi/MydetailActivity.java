package com.example.allrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MydetailActivity extends AppCompatActivity {

    private  int positioncountry;

    TextView textViewcases,textViewrecover,textViewcritical,textViewactive,textViewtodaycases,textViewdeaths,textViewtodaydeaths,textViewcounty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydetail);


        Intent intent=getIntent();
        positioncountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+AffectedCountries.countrymodelList.get(positioncountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewcases=findViewById(R.id.tvcases);
        textViewrecover=findViewById(R.id.tvrecovered);
        textViewcritical=findViewById(R.id.tvcritical);
        textViewactive=findViewById(R.id.tvactive);
        textViewtodaycases=findViewById(R.id.tvtodaycases);
        textViewdeaths=findViewById(R.id.totaldeaths);
        textViewtodaydeaths=findViewById(R.id.tvtodaydeaths);
        textViewcounty=findViewById(R.id.tvcountry);


        textViewcases.setText( AffectedCountries.countrymodelList.get(positioncountry).getCases());
        textViewrecover.setText(AffectedCountries.countrymodelList.get(positioncountry).getRecover());
        textViewcritical.setText(AffectedCountries.countrymodelList.get(positioncountry).getCritical());
        textViewactive.setText(AffectedCountries.countrymodelList.get(positioncountry).getActive());
        textViewtodaycases.setText(AffectedCountries.countrymodelList.get(positioncountry).getTodaycases());
        textViewdeaths.setText(AffectedCountries.countrymodelList.get(positioncountry).getDeaths());
        textViewtodaydeaths.setText(AffectedCountries.countrymodelList.get(positioncountry).getTodaydeaths());
        textViewcounty.setText(AffectedCountries.countrymodelList.get(positioncountry).getCountry());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home);
        finish();

        return super.onOptionsItemSelected(item);
    }
}
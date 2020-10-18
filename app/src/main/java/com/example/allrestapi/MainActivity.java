package com.example.allrestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {


    TextView tvcases,tvrecovered,tvcritical,tvactive,tvtodaycase,tvtodaydeaths,tvtotaldeaths,tvaffectedcountries;

    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    Button buttontrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvcases=findViewById(R.id.tvcases);
        tvrecovered=findViewById(R.id.tvrecovered);
        tvcritical=findViewById(R.id.tvcritical);
        tvactive=findViewById(R.id.tvactive);
        tvtodaycase=findViewById(R.id.tvtodaycases);
        tvtodaydeaths=findViewById(R.id.tvtodaydeaths);
        tvtotaldeaths=findViewById(R.id.totaldeaths);
        tvaffectedcountries=findViewById(R.id.Affectedcountries);

        buttontrack=findViewById(R.id.btntrack);

        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.srollviewstats);
        pieChart=findViewById(R.id.piechart);



        fetchData();

        buttontrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(MainActivity.this,AffectedCountries.class));

            }
        });
    }

    private void fetchData() {

        String url="https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());

                    tvcases.setText(jsonObject.getString("cases"));
                    tvrecovered.setText(jsonObject.getString("recovered"));
                    tvcritical.setText(jsonObject.getString("critical"));
                    tvactive.setText(jsonObject.getString("active"));
                    tvtodaycase.setText(jsonObject.getString("todayCases"));
                    tvtodaydeaths.setText(jsonObject.getString("todayDeaths"));
                    tvtotaldeaths.setText(jsonObject.getString("deaths"));
                    tvaffectedcountries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#fb7268")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvrecovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvtotaldeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#29B6F6")));

                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.INVISIBLE);
                    scrollView.setVisibility(View.VISIBLE);



                } catch (JSONException e) {
                    e.printStackTrace();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.INVISIBLE);
                    scrollView.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error .. "+error.getMessage(), Toast.LENGTH_SHORT).show();


                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
package com.example.allrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    EditText editTextsearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<ModelCountry> countrymodelList=new ArrayList<>();

    ModelCountry modelCountry;
    CountryAdapter countryAdapter;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        editTextsearch=findViewById(R.id.edittextsearch);
        listView=findViewById(R.id.listview);
        simpleArcLoader=findViewById(R.id.myloaders);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(AffectedCountries.this,MydetailActivity.class).putExtra("position",i));
            }
        });

        editTextsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                myAdapter.getFilter().filter(charSequence);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void fetchData() {

        String url="https://corona.lmao.ninja/v2/countries/";

        simpleArcLoader.start();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);

                    for (int i=0;i<array.length();i++){

                        JSONObject jsonObject= (JSONObject) array.get(i);
                        String countryname=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todaycases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todaydeaths=jsonObject.getString("todayDeaths");
                        String recover=jsonObject.getString("recovered");
                        String critcal=jsonObject.getString("critical");
                        String active=jsonObject.getString("active");

                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flagurl=object.getString("flag");

                        modelCountry=new ModelCountry(flagurl,countryname,cases,todaycases,deaths,todaydeaths,recover,active,critcal);
                        countrymodelList.add(modelCountry);

                        Toast.makeText(AffectedCountries.this, "cases"+cases, Toast.LENGTH_SHORT).show();
                        Log.d("Mytag",""+cases);
                    }
/*
                     countryAdapter=new CountryAdapter(AffectedCountries.this,countrymodelList);
                    listView.setAdapter(countryAdapter);*/

                    myAdapter=new MyAdapter(AffectedCountries.this, (ArrayList<ModelCountry>) countrymodelList);
                    listView.setAdapter(myAdapter);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AffectedCountries.this, "Error .. "+error.getMessage(), Toast.LENGTH_SHORT).show();

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home);
            finish();

        return super.onOptionsItemSelected(item);
    }
}
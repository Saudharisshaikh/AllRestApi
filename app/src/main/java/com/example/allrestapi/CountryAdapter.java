package com.example.allrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<ModelCountry> {

    Context context;
    List<ModelCountry> arrayList;

    public CountryAdapter(@NonNull Context context,List<ModelCountry> arrayList ) {
        super(context, R.layout.customitemlist);

        context=this.context;
        arrayList=this.arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row=LayoutInflater.from(parent.getContext()).inflate(R.layout.customitemlist,null,true);
        TextView textView=row.findViewById(R.id.countryname);
        ImageView imageView=row.findViewById(R.id.countryflag);

        textView.setText(arrayList.get(position).getCountry());
//        Picasso.get().load(arrayList.get(position).getFlag()).into(imageView);
        Glide.with(context).load(arrayList.get(position).getFlag()).into(imageView);
    return row;
    }


}

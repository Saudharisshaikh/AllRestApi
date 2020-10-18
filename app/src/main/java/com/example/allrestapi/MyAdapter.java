package com.example.allrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<ModelCountry> arrayList=new ArrayList<>();
    ArrayList<ModelCountry> temparraylist;
    filter fs;

    LayoutInflater layoutInflater;

    public MyAdapter(Context context, ArrayList<ModelCountry> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.temparraylist=arrayList;

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row=view;
        Vholders vholders;
        final  int position=i;
        if (row==null){
            vholders=new Vholders();
            row=layoutInflater.inflate(R.layout.customitemlist,null);

            vholders.imageView=row.findViewById(R.id.countryflag);
            vholders.textView=row.findViewById(R.id.countryname);

            row.setTag(vholders);
        }
        else {

           vholders= (Vholders) row.getTag();
        }

        Glide.with(context).load(arrayList.get(position).getFlag()).into(vholders.imageView);
        vholders.textView.setText(arrayList.get(position).getCountry());

        return row;
    }

    @Override
    public Filter getFilter() {

        if (fs==null){

            fs=new filter();
        }
        return fs;
    }

    public static class Vholders{

        ImageView imageView;
        TextView textView;
    }

    class  filter extends Filter{


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            FilterResults filterResults=new FilterResults();
            if (charSequence!=null&&charSequence.length()>0){

                charSequence=charSequence.toString().toUpperCase();
                ArrayList<ModelCountry> filters=new ArrayList<>();

                for (int i=0;i<temparraylist.size();i++){

                    if (temparraylist.get(i).getCountry().toUpperCase().contains(charSequence)){

                        ModelCountry modelCountry=new ModelCountry(temparraylist.get(i).getFlag(),temparraylist.get(i).getCountry(),temparraylist.get(i).getCases(),temparraylist.get(i).getTodaycases(),temparraylist.get(i).getDeaths(),temparraylist.get(i).getTodaydeaths(),temparraylist.get(i).getRecover(),temparraylist.get(i).getActive(),temparraylist.get(i).getCritical());
                   //     ModelCountry modelCountry=new ModelCountry(temparraylist.get(i).getCountry());
                        filters.add(modelCountry);
                    }
                }
                filterResults.count=filters.size();
                filterResults.values=filters;

            }
            else {

                filterResults.count=temparraylist.size();
                filterResults.values=temparraylist;
            }

          return   filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            arrayList= (ArrayList<ModelCountry>) filterResults.values;
        notifyDataSetChanged();
        }
    }
}

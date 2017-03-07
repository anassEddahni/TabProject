package com.example.dit.tabproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DIT on 07/03/2017.
 */

class MyAdapter extends ArrayAdapter<String>{
    Context context;
    int[] imgs;
    String [] titleArray;
    String [] descArray;
    int count = 0;



    public MyAdapter(Context c, String[] titles , int imgs[] , String[] desc){
        super(c,R.layout.single_row,R.id.mytitle,titles);
        this.context=c;
        this.imgs = imgs;
        this.titleArray = titles;
        this.descArray = desc;
    }

    class MyHolder{
        ImageView myImage ;
        TextView myTitle ;
        TextView myDesc ;
        MyHolder(View v){
            myImage = (ImageView) v.findViewById(R.id.imageView);
            myTitle = (TextView) v.findViewById(R.id.mytitle);
            myDesc = (TextView) v.findViewById(R.id.descrip);
        }


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        MyHolder holder = null;
        if(row== null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder = new MyHolder(row);
            row.setTag(holder);
            Log.d("iterration", "count" + count++);

        }else {
            holder = (MyHolder) row.getTag();
        }
            /*
            on remplace par holder
            ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
            TextView myTitle = (TextView) row.findViewById(R.id.mytitle);
            TextView myDesc = (TextView) row.findViewById(R.id.descrip);
*/
        holder.myImage.setImageResource(imgs [position]);
        holder.myTitle.setText(titleArray [position]);
        holder.myDesc.setText(descArray [position]);

        return row;



    }

}
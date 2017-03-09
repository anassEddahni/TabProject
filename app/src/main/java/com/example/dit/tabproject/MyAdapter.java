package com.example.dit.tabproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DIT on 07/03/2017.
 */

class MyAdapter extends ArrayAdapter<String>{
    Context context;
    int[] imgs;
    String [] titleArray;
    String [] descArray;
    int overflow;
    int count = 0;
    Button overflowButton;


    public MyAdapter(Context c, String[] titles , int imgs[] , String[] desc,int overflow){
        super(c,R.layout.single_row,R.id.mytitle,titles);
        this.context=c;
        this.imgs = imgs;
        this.titleArray = titles;
        this.descArray = desc;
        this.overflow = overflow;
    }

    class MyHolder{
        ImageView myImage ;
        TextView myTitle ;
        TextView myDesc ;
        Button myOverflow;
        MyHolder(View v){
            myImage = (ImageView) v.findViewById(R.id.imageView);
            myTitle = (TextView) v.findViewById(R.id.mytitle);
            myDesc = (TextView) v.findViewById(R.id.descrip);
            myOverflow = (Button) v.findViewById(R.id.overflow);
        }


    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {

        View row = convertView;
        MyHolder holder = null;
        if(row== null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder = new MyHolder(row);
            final Button over = holder.myOverflow;

            //onclick sur popup menu
            over.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(getContext(),  over);

                    Log.d("flag 2","rààààààààààààààààààààà ==================");
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_main, popup.getMenu());
                    Log.d("flag 3","================================");

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(getContext(),"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    popup.show();//showing popup menu
                }
            });
            row.setTag(holder);
            Log.d("iteration", "count" + count++);

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
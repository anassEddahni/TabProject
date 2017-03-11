package com.example.dit.tabproject;


import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * Created by DIT on 05/03/2017.
 */

public class MostDownload extends Fragment  {
    SQLiteDatabaseManager sqLiteDatabaseManager;
    String[] names;
    String[] emails;
    int[] images={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10};
    ListView listView ;
    int bouton = R.drawable.overflow;
    Button imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_download, container, false);
        View rowView = inflater.inflate(R.layout.single_row, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_download);
        imageView = (Button) rowView.findViewById(R.id.overflow);
        Log.d("flag 1","recuperation de l'overflow ==================");

        //onclick sur l'overflow icon
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), imageView);
                Log.d("flag 2","rààààààààààààààààààààà ==================");
                rootView.setBackgroundColor(Color.rgb(217,255,253));
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                Log.d("flag 3","================================");

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getContext(),"You Clicked option : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });

        sqLiteDatabaseManager = new SQLiteDatabaseManager(getActivity());
        String[] nom = sqLiteDatabaseManager.getNames();
        String[] email = sqLiteDatabaseManager.getEmails();


        Resources r = getResources();
        names= nom;
        emails= email;
        //emails= r.getStringArray(R.array.desc);    recuperer depuis ressource
        MyAdapter myAdapter = new MyAdapter(getActivity(),names,images,emails,bouton);
        listView.setAdapter(myAdapter);
        return rootView;

       /*Array avec Strings
       listView = (ListView) rootView.findViewById(R.id.list_download);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);*/

    }
}

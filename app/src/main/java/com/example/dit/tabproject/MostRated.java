package com.example.dit.tabproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by DIT on 05/03/2017.
 */

public class MostRated extends Fragment {
    String[] titles;
    String[] desc;
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10};
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rated, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_rated);

        Resources r = getResources();
        titles = r.getStringArray(R.array.title);
        desc = r.getStringArray(R.array.desc);
        MyAdapter myAdapter = new MyAdapter(getActivity(), titles, images, titles);
        listView.setAdapter(myAdapter);
       /* listView = (ListView) rootView.findViewById(R.id.list_download);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);*/
        return rootView;
    }
}

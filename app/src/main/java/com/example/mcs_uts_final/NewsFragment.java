package com.example.mcs_uts_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NewsFragment extends Fragment {

    private ArrayList<News> newsArrayList;
    private String[] newsHeading;
    private int[] imageResource;
    private RecyclerView recylerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        dataInitialize();
        recylerview = view.findViewById(R.id.recylerview);
        recylerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recylerview.setHasFixedSize(true);
        AdapterHome adapterHome = new AdapterHome(getContext(),newsArrayList);
        recylerview.setAdapter(adapterHome);
        adapterHome.notifyDataSetChanged();
    }

    private void dataInitialize() {
        newsArrayList = new ArrayList<>();
        newsHeading = new String[]{
                getString(R.string.N1),
                getString(R.string.N2),
                getString(R.string.N3),
                getString(R.string.N4),
                getString(R.string.N5),
                getString(R.string.N6),
                getString(R.string.N7),
        };
        imageResource = new int[]{
                R.drawable.img,
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4,
                R.drawable.img_5,
                R.drawable.img_6,
        };
        for (int j=0; j< newsHeading.length; j++){
            News news = new News(newsHeading[j],imageResource[j]);
            newsArrayList.add(news);
         }
    }
}

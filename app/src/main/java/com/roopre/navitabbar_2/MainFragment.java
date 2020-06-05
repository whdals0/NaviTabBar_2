package com.roopre.navitabbar_2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import devlight.io.library.ntb.NavigationTabBar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    View rootView;

    ViewPager viewPager;
    MainAdapter mainAdapter;
    List<Fragment> fragments = new ArrayList<>();
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_main, container, false);;

        viewPager = rootView.findViewById(R.id.viewPager);

        GalleryFragment galleryFragment = new GalleryFragment();
        HomeFragment homeFragment = new HomeFragment();
        SlideShowFragment slideShowFragment = new SlideShowFragment();


        fragments.clear();
        fragments.add(galleryFragment);
        fragments.add(homeFragment);
        fragments.add(slideShowFragment);

        mainAdapter = new MainAdapter(getChildFragmentManager(), fragments);
        mainAdapter.notifyDataSetChanged();

        viewPager.setAdapter(mainAdapter);


        final NavigationTabBar navigationTabBar = (NavigationTabBar) rootView.findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_gallery),
                        Color.parseColor("#FF0000")
                ).title("Gallery")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home),
                        Color.parseColor("#00FF00")
                ).title("Home")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_slideshow),
                        Color.parseColor("#0000FF")
                ).title("Show")
                        .badgeTitle("state")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager);


//        navigationTabBar.setViewPager(viewPager, 2);


        return rootView;
    }
}

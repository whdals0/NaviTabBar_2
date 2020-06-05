package com.roopre.navitabbar_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlideShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlideShowFragment extends Fragment {

    View rootView;
    public SlideShowFragment() {
        // Required empty public constructor
    }

    public static SlideShowFragment newInstance(String param1, String param2) {
        SlideShowFragment fragment = new SlideShowFragment();
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
        rootView = inflater.inflate(R.layout.fragment_slideshow, container, false);;

        return rootView;
    }
}

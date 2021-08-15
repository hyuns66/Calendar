package com.example.calendar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    public ArrayList<Object> mItemList;

    RecyclerView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = rootView.findViewById(R.id.view_calendar);



        return rootView;
    }

}

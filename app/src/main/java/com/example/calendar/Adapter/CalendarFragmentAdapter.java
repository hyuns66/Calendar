    package com.example.calendar.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.calendar.R;
import com.example.calendar.fragment.CalendarFragment;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragmentAdapter extends RecyclerView.Adapter<CalendarFragmentAdapter.CalendarPagerViewHolder> {

    private ArrayList<ArrayList<Object>> mPagerList; //calendarList_month 리스트를 멤버로 가지고 있는 리스트

    public CalendarFragmentAdapter(ArrayList<ArrayList<Object>> pagerList){
        mPagerList = pagerList;
    }

    public class CalendarPagerViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView calendarView;
        private StaggeredGridLayoutManager manager;
        private CalendarAdapter mAdapter;
        ArrayList<Object> dataList;

        public CalendarPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarView = itemView.findViewById(R.id.view_calendar);
        }

        public void onBind(ArrayList<Object> dataList){

            //dataList에 데이터들 집어넣고 달력박기
            this.dataList = dataList;
            setRecycler();
        }

        public void setRecycler(){
            if (dataList == null){
                Log.w("DATA ERR", "No dataset !!");
            }

            manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
            mAdapter = new CalendarAdapter(dataList);

            mAdapter.setCalendarList(dataList);
            calendarView.setLayoutManager(manager);
            calendarView.setAdapter(mAdapter);
        }
    }


    @NonNull
    @Override
    public CalendarPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_calendar, parent, false);
        return new CalendarPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarPagerViewHolder holder, int position) {
        if(holder instanceof CalendarPagerViewHolder){
            CalendarPagerViewHolder viewHolder = (CalendarPagerViewHolder) holder;
            viewHolder.onBind(mPagerList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPagerList.size();
    }
}

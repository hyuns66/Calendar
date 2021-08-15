package com.example.calendar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.viewmodel.DayModel;
import com.example.calendar.viewmodel.EmptyDayModel;
import com.example.calendar.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {

    private final int EMPTY_TYPE = 0;
    private final int DAY_TYPE = 1;


    private ArrayList<Object> mCalendarList;

    public CalendarAdapter(ArrayList<Object> calendarList) {
        mCalendarList = calendarList;
    }

    public void setCalendarList(ArrayList<Object> calendarList){
        mCalendarList = calendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) { //뷰타입 나누기
        Object item = mCalendarList.get(position);
        if (item instanceof String) {
            return EMPTY_TYPE; // 비어있는 일자 타입
        } else {
            return DAY_TYPE; // 일자 타입
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == EMPTY_TYPE){
            view = inflater.inflate(R.layout.item_calendar_emptyday, parent, false);
            return new EmptyViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_calendar_day, parent, false);
            return new DateViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        int viewType = getItemViewType(position);
        if(viewType == EMPTY_TYPE){
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
            EmptyDayModel model = new EmptyDayModel();
            holder.bind(model);
        }

        else if (viewType == DAY_TYPE) {
            DateViewHolder holder = (DateViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            DayModel model = new DayModel();
            if (item instanceof Calendar){
                model.setCalendar((Calendar) item);
            }
            holder.bind(model);
        }
    }

    @Override
    public int getItemCount() {
        if (mCalendarList != null) {
            return mCalendarList.size();
        }
        return 0;
    }


    private class EmptyViewHolder extends RecyclerView.ViewHolder { //비어있는 타입 ViewHolder
        EmptyViewHolder(View itemView){
            super(itemView);
            initView(itemView);
        }
        public void initView(View v){

        }
        public void bind(ViewModel model){

        }
    }


    private class DateViewHolder extends RecyclerView.ViewHolder { // 비어있는 요일 타입 ViewHolder
        TextView date;

        DateViewHolder(View itemView){
            super(itemView);
            initView(itemView);
        }
        public void initView(View v){
            date = (TextView) v.findViewById(R.id.tv_date);
        }
        public void bind(ViewModel model){
            String day = ((DayModel)model).getDate();
            date.setText(day);
        }
    }

}

package com.example.calendar.viewmodel;

import com.example.calendar.util.DateUtil;

import java.util.Calendar;

public class DayModel extends ViewModel{

    String date;

    public DayModel(){

    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setCalendar(Calendar calendar){
        date = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.DAY_FORMAT);
    }
}

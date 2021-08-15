package com.example.calendar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.calendar.Adapter.CalendarAdapter;
import com.example.calendar.Adapter.CalendarFragmentAdapter;
import com.example.calendar.R;
import com.example.calendar.util.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends FragmentActivity {

    public ArrayList<Object> mCalendarList_month = new ArrayList<>();
    public ArrayList<ArrayList<Object>> mCalendarList_total = new ArrayList<>();
    public TextView tv_dateToday;
    public int mCenterPosition;
    public ViewPager2 calendarPager;
    public CalendarFragmentAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCalendar();

        //calendar 뷰페이저 어댑터 설정
        calendarPager = findViewById(R.id.calendar_pager);
        mPagerAdapter = new CalendarFragmentAdapter(mCalendarList_total);
        calendarPager.setAdapter(mPagerAdapter);
        //현재 달을 기준페이지로 설정
        calendarPager.setCurrentItem(mCenterPosition);

        tv_dateToday = (TextView) findViewById(R.id.tv_dateToday);


    }


    public void setCalendar(){
        // 오늘 날짜 가져옴
        GregorianCalendar cal = new GregorianCalendar();

        for (int i = -300; i < 300; i++){
            try{
                // 오늘날짜 기준 +- 1달씩 50번씩 해서 데이터 저장
                ArrayList<Object> calendarList = new ArrayList<>();
                GregorianCalendar calendar = new GregorianCalendar(
                        cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+i, 1, 0, 0, 0);

                if (i == 0){
                    mCenterPosition = mCalendarList_total.size();
                }
//                calendarList.add(calendar.getTimeInMillis()); // 날짜타입 저장

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; // 달의 시작요일 - 1
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 달의 마지막 날짜

                for (int j=0; j<dayOfWeek; j++){
                    calendarList.add(Keys.EMPTY); // 비어있는 일자타입 저장
                }
                for(int j=1; j<=max; j++){
                    // 일자 타입 저장
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),j));
                }
                mCalendarList_month = calendarList;
//                Log.d("mCalendarList_month", String.valueOf(mCalendarList_month));
                mCalendarList_total.add(mCalendarList_month);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
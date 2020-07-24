package com.jy.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jy.geeknews.base.Constants;
import com.jy.geeknews.util.DateUtil;
import com.jy.geeknews.util.ToastUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.view_calender)
    MaterialCalendarView mViewCalender;
    @BindView(R.id.tv_calender_enter)
    TextView mTvCalenderEnter;

    private CalendarDay mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        //设置日历
        mViewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)//设置每周的第一天
                .setMinimumDate(CalendarDay.from(2018, 9, 3))//设置日历最早日期
                //设置最新日期,应该动态获取
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mViewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();
                mDate = date;//设置为全局的日期
//                ToastUtil.showShort(year+"年"+month+"月"+day);
            }
        });

    }

    @OnClick(R.id.tv_calender_enter)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_calender_enter:
               returnDate();
                break;
        }
    }

    public void returnDate(){
        int year = mDate.getYear();
        int month = mDate.getMonth()+1;
        int day = mDate.getDay();

        String _month = ""+month;
        if(month<10){
            _month = "0"+month;
        }
        String _day = ""+day;
        if(day<10){
            _day = "0"+day;
        }

        String dateStr = year+""+_month+""+_day;//把年月日拼接成要的字符串

        Intent it = new Intent();
        it.putExtra(Constants.DATA,dateStr);//把日期传回到日报新闻页面
        setResult(100,it);
        finish();
    }

}

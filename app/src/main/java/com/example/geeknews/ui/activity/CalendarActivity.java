package com.example.geeknews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geeknews.R;
import com.example.geeknews.util.LogUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialCalendarView mCalendarView;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        Calendar instance = Calendar.getInstance();
        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)//设置一个星期的第一天
                .setMinimumDate(CalendarDay.from(2019, 12, 3))//设置最早可以翻到的日期
                .setMaximumDate(CalendarDay.from(instance.get(Calendar.YEAR),
                        instance.get(Calendar.MONTH),
                        instance.get(Calendar.DAY_OF_MONTH)))//设置最大可以翻的的日期
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置日历的显示模式
                .commit();
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                commit();
                break;
            default:
                break;
        }
    }

    private void commit() {
        //注意java中日历使用的是 格里高利历,月份是从0开始的
        CalendarDay selectedDate = mCalendarView.getSelectedDate();

        EventBus.getDefault().post(selectedDate);
        finish();
    }
}

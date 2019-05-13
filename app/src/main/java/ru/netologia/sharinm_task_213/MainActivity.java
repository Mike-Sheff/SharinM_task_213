package ru.netologia.sharinm_task_213;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndDateCalendar;
    private Button mBtnOK;
    private Button mBtnClear;

    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mStartDateTxt = i + "-" + i1 + "-" + i2;
                mChooseStartDate.setText("Дата-время старта задачи: " + mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);

                mEndDateCalendar.setVisibility(View.VISIBLE);
                mStartDateCalendar.setVisibility(View.GONE);
            }
        });

        mEndDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mEndDateTxt = i + "-" + i1 + "-" + i2;
                mChooseEndDate.setText("Дата-время окончания задачи: " + mEndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);

                mBtnOK.setVisibility(View.VISIBLE);
                mBtnClear.setVisibility(View.VISIBLE);
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStartDate > mEndDate) {
                    Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText("Дата-время старта задачи:");
                    mChooseEndDate.setText("Дата-время окончания задачи:");

                    mStartDateCalendar.setVisibility(View.VISIBLE);
                    mEndDateCalendar.setVisibility(View.GONE);
                    mBtnOK.setVisibility(View.INVISIBLE);
                    mBtnClear.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "старт: " + mStartDateTxt + " окончаниe: " + mEndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChooseStartDate.setText("Дата-время старта задачи:");
                mChooseEndDate.setText("Дата-время окончания задачи:");

                mStartDateCalendar.setVisibility(View.VISIBLE);
                mEndDateCalendar.setVisibility(View.GONE);

                mBtnOK.setVisibility(View.INVISIBLE);
                mBtnClear.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndDateCalendar = findViewById(R.id.endDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);
        mBtnClear = findViewById(R.id.btnClear);

        // Скроем календари при запуске приложения
        mBtnOK.setVisibility(View.INVISIBLE);
        mBtnClear.setVisibility(View.INVISIBLE);
        mStartDateCalendar.setVisibility(View.VISIBLE);
        mEndDateCalendar.setVisibility(View.GONE);
    }
}

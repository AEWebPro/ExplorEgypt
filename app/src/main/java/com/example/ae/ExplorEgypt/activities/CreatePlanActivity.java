package com.example.ae.ExplorEgypt.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.ae.ExplorEgypt.R;
import com.example.ae.ExplorEgypt.modules.Plan;
import com.example.ae.ExplorEgypt.modules.SessionPlan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePlanActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    String startDate = "";
    String endDate = "";

    private TextInputEditText planName;
    private TextView planDuration;

    private SessionPlan createdSessionPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        createdSessionPlan = SessionPlan.getSessionPlanInstance();
        planName = (TextInputEditText) findViewById(R.id.activity_plan_name);
        planDuration = (TextView) findViewById(R.id.activity_plan_duration);

        Button saveButton = (Button) findViewById(R.id.activity_create_plan_btn);

        Plan receivedPlan = getIntent().getParcelableExtra("edit_plan");
        if (receivedPlan != null) {
            saveButton.setText("Save");

            createdSessionPlan.setPlanName(receivedPlan.getPlanName());
            createdSessionPlan.setPlanStartDate(receivedPlan.getPlanStartDate());
            createdSessionPlan.setPlanEndDate(receivedPlan.getPlanStartDate());
            createdSessionPlan.setPairOfData(receivedPlan.getPairOfData());

            planName.setText(receivedPlan.getPlanName());
            planDuration.setText(Integer.toString(getDuration(receivedPlan.getPlanStartDate(), receivedPlan.getPlanEndDate())) + " Days");

        }

        ButterKnife.bind(this);

    }

    @OnClick(R.id.activity_plan_date)
    public void createDatePickerDialoge() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                CreatePlanActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.activity_create_plan_btn)
    public void openSelectPlaceTypeScreen() {

        //check if not empty
        if (planName.getText().toString().isEmpty()) {
            Toast.makeText(this, "The Trip plan must have a title!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (planDuration.getText().toString().isEmpty()) {
            Toast.makeText(this, "Start & End date can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        createdSessionPlan.setPlanName(planName.getText().toString());
        createdSessionPlan.setPlanStartDate(startDate);
        createdSessionPlan.setPlanEndDate(endDate);

        //TODO: add a Notification when is created
        startActivity(new Intent(this, SelectTypeToAddActivity.class));

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

        startDate = Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear) + "/" + Integer.toString(year);
        endDate = Integer.toString(dayOfMonthEnd) + "/" + Integer.toString(monthOfYearEnd) + "/" + Integer.toString(yearEnd);

        planDuration.setText(Integer.toString(getDuration(startDate, endDate) )+ " Days");
    }

    public static int getDuration(String startDate, String endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date Created_convertedDate = null, Expire_CovertedDate = null;
        try {
            Created_convertedDate = dateFormat.parse(startDate);
            Expire_CovertedDate = dateFormat.parse(endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar s_cal = Calendar.getInstance();
        s_cal.setTime(Created_convertedDate);

        Calendar e_cal = Calendar.getInstance();
        e_cal.setTime(Expire_CovertedDate);

        long diff = e_cal.getTimeInMillis() - s_cal.getTimeInMillis();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        int duration = (int) Math.ceil(dayCount);

        return duration;
    }

    /*
    public String get_count_of_days(String Created_date_String, String Expire_date_String) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date Created_convertedDate = null, Expire_CovertedDate = null, todayWithZeroTime = null;
        try {
            Created_convertedDate = dateFormat.parse(Created_date_String);
            Expire_CovertedDate = dateFormat.parse(Expire_date_String);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int c_year = 0, c_month = 0, c_day = 0;

        if (Created_convertedDate.after(todayWithZeroTime)) {
            Calendar c_cal = Calendar.getInstance();
            c_cal.setTime(Created_convertedDate);
            c_year = c_cal.get(Calendar.YEAR);
            c_month = c_cal.get(Calendar.MONTH);
            c_day = c_cal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar c_cal = Calendar.getInstance();
            c_cal.setTime(todayWithZeroTime);
            c_year = c_cal.get(Calendar.YEAR);
            c_month = c_cal.get(Calendar.MONTH);
            c_day = c_cal.get(Calendar.DAY_OF_MONTH);
        }

        Calendar e_cal = Calendar.getInstance();
        e_cal.setTime(Expire_CovertedDate);

        int e_year = e_cal.get(Calendar.YEAR);
        int e_month = e_cal.get(Calendar.MONTH);
        int e_day = e_cal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(c_year, c_month, c_day);
        date2.clear();
        date2.set(e_year, e_month, e_day);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return ("" + (int) dayCount + " Days");
    }*/
}

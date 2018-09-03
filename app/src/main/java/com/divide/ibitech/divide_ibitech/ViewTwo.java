package com.divide.ibitech.divide_ibitech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewTwo extends AppCompatActivity {
    TextView viewsymptoms, visitDate;
    String url = "http://sict-iis.nmmu.ac.za/ibitech/app/getSymp.php";
    EditText diagnosCondition, diagnosDoctor, ConditionMedicationName, medicationDescription, medicationDosage, medicationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_two);
        viewsymptoms = findViewById(R.id.tvSymptom1);
        diagnosDoctor = (EditText) findViewById(R.id.spDoctor);
        diagnosCondition = (EditText) findViewById(R.id.spDiagnosis);

        visitDate = findViewById(R.id.tvDate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        visitDate.setText(dateFormat.format(date));


    }
}
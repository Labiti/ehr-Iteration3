package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddAllergy extends AppCompatActivity {
    String URL_ALLERGY= "http://sict-iis.nmmu.ac.za/ibitech/app-test/addcondition.php";
    Spinner sp_Severity;
    AutoCompleteTextView autoCompleteTextView;
    String[] AllergyName;
    String idNumber, allergy,addDate,severity;
    TextView tv_date;
    EditText etAllName,tvtID;

    Button btn_add,btn_cancel;
    SharedPreferences prefs;
    Boolean cValid = false;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_allergy);

        sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getUserDetails();
        idNumber = user.get(sessionManager.ID);



        autoCompleteTextView = findViewById(R.id.tvAllergy);
        AllergyName = getResources().getStringArray(R.array.allergies);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,AllergyName);
        autoCompleteTextView.setAdapter(adapter);

        //Load severity spinner
        sp_Severity = findViewById(R.id.spnSeverity);
        ArrayAdapter<CharSequence> severityAdapter = ArrayAdapter.createFromResource(this,R.array.severity_level,R.layout.support_simple_spinner_dropdown_item);
        severityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_Severity.setAdapter(severityAdapter);

        sp_Severity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                severity = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?>parent){

            }
        });


        btn_add = findViewById(R.id.btnAdd);
        btn_cancel = findViewById(R.id.btnCancel);
        prefs = getSharedPreferences("REGP",MODE_PRIVATE);
        idNumber = prefs.getString("pID","");

        tv_date = findViewById(R.id.tvDate);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        tv_date.setText(dateFormat.format(date));

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAllergy.this,Dashboard.class));
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //Real-time validation
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(autoCompleteTextView.getText().length() > 0){

                }
            }
        });
    }
}

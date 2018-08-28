package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddSymptom extends AppCompatActivity {
    String URL_symptom="";
TextView tv_date;
    String idNumber, condition,addDate,severity;
    Button btn_add,btn_cancel;
    String symtoms;
    SessionManager sessionManager;
EditText etSym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptom);
        etSym =(EditText) findViewById(R.id.etSymptoms);
        btn_cancel = findViewById(R.id.btnCancel);
        btn_add= findViewById(R.id.btnAdd);
        tv_date =(TextView) findViewById(R.id.tvDate);

        sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getUserDetails();
        idNumber = user.get(sessionManager.ID);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        tv_date.setText(dateFormat.format(date));

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddSymptom.this,Dashboard.class));
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String symptoms =etSym.getText().toString();
                final  String sydate =tv_date.getText().toString();
                if (symptoms.isEmpty()) {
                    etSym.setError("Please enter a symptoms");
                    Toast.makeText(AddSymptom.this, "Make sure you have entered all the values. ", Toast.LENGTH_LONG).show();

                }
                else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_symptom,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        String message = jsonObject.getString("message");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Toast toast = Toast.makeText(AddSymptom.this, response, Toast.LENGTH_LONG);
                                    toast.show();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> paramas = new HashMap<String, String>();
                            paramas.put("stmptom_id", symptoms);
                            paramas.put("date", sydate);
                            paramas.put("patient_id", idNumber);

                            return paramas;
                        }
                    };


                }


            }
        });

        }
}

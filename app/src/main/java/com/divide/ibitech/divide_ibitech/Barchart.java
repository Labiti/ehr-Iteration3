package com.divide.ibitech.divide_ibitech;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Barchart extends AppCompatActivity {
    private ProgressDialog pd;

    ArrayList yAxis;
    ArrayList yValues;
    ArrayList xAxis1;
    BarEntry values ;
    BarChart chart;
    BarData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        pd = new ProgressDialog(Barchart.this);
        pd.setMessage("loading");


        // Log.d("array",Arrays.toString(fullData));
        chart = (BarChart) findViewById(R.id.barchart);
        load_data_from_server();
    }
    public void load_data_from_server() {
        pd.show();
        String URL = "http://sict-iis.nmmu.ac.za/ibitech/app-test/GRAPH.php";
        // String url = "http://vga.ramstertech.com/freebieslearning/chart.php";
        xAxis1 = new ArrayList<>();
        yAxis = null;
        yValues = new ArrayList<>();

        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);

                    //JSONArray jsonarray = new JSONArray(response);
                    JSONArray array =obj.getJSONArray("server_response");


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jsonobject = array.getJSONObject(i);

                        String score = jsonobject.getString("number").trim();
                        String name = jsonobject.getString("allergy_name").trim();

                        xAxis1.add(name);

                        values = new BarEntry(Float.valueOf(score), i);
                        yValues.add(values);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                BarDataSet barDataSet1 = new BarDataSet(yValues, "Most frequent Allergies ");
                barDataSet1.setColor(Color.rgb(0, 82, 159));

                yAxis = new ArrayList<>();
                yAxis.add(barDataSet1);
                String names[]= (String[]) xAxis1.toArray(new String[xAxis1.size()]);
                //  String names[]= xAxis1.toArray(new String[xAxis1.size()]);
                data = new BarData(names,yAxis);
                chart.setData(data);
                chart.setDescription("");
                chart.animateXY(2000, 2000);
                chart.invalidate();
                pd.hide();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error!=null){

                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                    pd.hide();

                }
            }
        }
        );
        Singleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

    }

}

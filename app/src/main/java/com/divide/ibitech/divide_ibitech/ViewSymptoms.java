package com.divide.ibitech.divide_ibitech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.divide.ibitech.divide_ibitech.Adapter.SymptomListAdapter;
import com.divide.ibitech.divide_ibitech.Model.SymptomsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewSymptoms extends AppCompatActivity {
ListView listView;
String URLgetSymptoms = "http://sict-iis.nmmu.ac.za/ibitech/app-test/getSymptoms.php";

List <SymptomsList> sympLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_symptoms);
        listView=(ListView)findViewById(R.id.listSymp);
        sympLists= new ArrayList<>();
ShowList();
    }
    private  void  ShowList(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLgetSymptoms,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array=obj.getJSONArray("server_response");
                            for (int i = 0;i< array.length();i++){
                                JSONObject  sympObj = array.getJSONObject(i);
                                SymptomsList Si = new SymptomsList(sympObj.getString("symptom_name"),sympObj.getString("date_added"));
                                sympLists.add(Si);
                            }
                            SymptomListAdapter  adapter = new SymptomListAdapter(sympLists,getApplication());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){


        };
        Singleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}

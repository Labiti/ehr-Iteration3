package com.divide.ibitech.divide_ibitech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.divide.ibitech.divide_ibitech.Adapter.AllergyListAdapter;
import com.divide.ibitech.divide_ibitech.Model.AllergyList;
import com.divide.ibitech.divide_ibitech.Model.ConditionList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewAllergy extends AppCompatActivity {
    ListView listView;
    String URLAllergy = "http://sict-iis.nmmu.ac.za/ibitech/app-test/getallergy.php";

    List<AllergyList> alleList;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allergy);
        listView=(ListView)findViewById(R.id.listAllergy);
        alleList= new ArrayList<>();
        ShowList();
    }

    private void ShowList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLAllergy,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array =obj.getJSONArray("server_response");
                            for (int   x= 0;x<array.length();x++){
                                JSONObject condOBJ= array.getJSONObject(x);
                                AllergyList alle = new AllergyList(condOBJ.getString("allergy_name"),condOBJ.getString("date_added"));
                                alleList.add(alle);

                            }
                            AllergyListAdapter adapter =  new AllergyListAdapter(alleList,getApplication());
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

package com.divide.ibitech.divide_ibitech.Adapter;
/**
 * Created by s216100801
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.divide.ibitech.divide_ibitech.Model.SymptomsList;
import com.divide.ibitech.divide_ibitech.R;

import java.security.PublicKey;
import java.util.List;

public class SymptomListAdapter extends ArrayAdapter<SymptomsList> {

    private List<SymptomsList> symptomsList;
    private Context mCtx;

    public SymptomListAdapter(List<SymptomsList> S,Context c){
        super(c, R.layout.symptoms_list,S);
        this.symptomsList=S;
        this.mCtx=c;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.symptoms_list,null,true);
        TextView symptom_name =(TextView) view.findViewById(R.id.etName);
        TextView date_added =(TextView) view.findViewById(R.id.etVisDate);
SymptomsList symptoms=  symptomsList.get(position);
        symptom_name.setText(symptoms.getSymptom_name());
        date_added.setText(symptoms.getDate_added());
      return  view;
    }
}

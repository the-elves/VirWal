package com.example.illuminati.virwall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ListManagement extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private Button jAdd;
    private EditText jEdit;
    private ListView jList;
    private int nolistItems;
    private SharedPreferences listItems;
    public ArrayList<String> listItemsString;
    ArrayAdapter<String> jLVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_management);
        jAdd = (Button) findViewById(R.id.lAdd);
        jEdit = (EditText) findViewById(R.id.lText);
        jList = (ListView) findViewById(R.id.lList);
        listItemsString = new ArrayList<String>();
        jAdd.setOnClickListener(this);
        listItems = getSharedPreferences("List_Items", MODE_PRIVATE);
        nolistItems = 0;
        jList.setOnItemClickListener(this);
        populateListView();
    }
    private void populateListView(){
        nolistItems = 0;
        Map<String,?> tempMap = listItems.getAll();
        Iterator itr = tempMap.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry thisone = (Map.Entry)itr.next();
            if((String)thisone.getValue() != ""){
                listItemsString.add((String)thisone.getValue());

            }
            nolistItems ++;
        }
        jLVAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItemsString);
        jList.setAdapter(jLVAdapter);
        jLVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lAdd:
                if(!jEdit.getText().equals("")) {
                    listItemsString.add(jEdit.getText().toString());
                    addToList(jEdit.getText().toString(), nolistItems);
                    jLVAdapter.notifyDataSetChanged();
                    nolistItems++;
                    jEdit.setText("");

                }
        }
    }


    private void addToList(String s, int n){
        SharedPreferences.Editor edit = listItems.edit();
        edit.putString(Integer.toString(n), s);
        edit.commit();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedStirng = ((TextView)view).getText().toString();
        SharedPreferences.Editor edit = listItems.edit();
        Log.d("ssw",selectedStirng);
        Map<String,?> tempMap = listItems.getAll();
        Iterator itr = tempMap.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry thisone = (Map.Entry)itr.next();
            if((String)thisone.getValue() == selectedStirng){
                edit.putString(thisone.getKey().toString(), "");
                break;
            }
        }
        edit.commit();
        listItemsString.clear();
        populateListView();
        //listItems.edit().clear();
    }
}



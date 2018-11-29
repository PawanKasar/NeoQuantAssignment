package com.neoquant.assignment.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.neoquant.assignment.Adapters.ContactAdapter;
import com.neoquant.assignment.CallingClasses.CallingImportantMethod;
import com.neoquant.assignment.DataBaseHelper.DataBaseOpenHelper;
import com.neoquant.assignment.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBaseOpenHelper db;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        getAllContactList();
    }

    public void initViews(){

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        db = new DataBaseOpenHelper(MainActivity.this);

    }

    public void getAllContactList(){
        try {
            //JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray  = new JSONArray(String.valueOf(db.getDataFromContactMaster()));

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Log.d("MainActivity","Getting StoreMaster Details "+jsonObject.toString());

            for (int i=0; i < jsonArray.length(); i++){
                JSONObject contactMasterObject = jsonArray.getJSONObject(i);
                Log.d("MainActivity","Printing "+contactMasterObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CallingImportantMethod.showToast(MainActivity.this,"No Data Present");
        }
    }
}

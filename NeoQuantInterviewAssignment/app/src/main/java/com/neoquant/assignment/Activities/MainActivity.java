package com.neoquant.assignment.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.neoquant.assignment.Adapters.ContactAdapter;
import com.neoquant.assignment.CallingClasses.CallingImportantMethod;
import com.neoquant.assignment.DataBaseHelper.DataBaseOpenHelper;
import com.neoquant.assignment.Models.ContactMasterModel;
import com.neoquant.assignment.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBaseOpenHelper db;
    ContactAdapter contactAdapter;
    SwipeRefreshLayout refreshLayout;
    ArrayList<ContactMasterModel> modelClassArrayList;
    ContactMasterModel contactMasterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        getAllContactList();
    }

    public void initViews(){

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.pulltorefresh);

        db = new DataBaseOpenHelper(MainActivity.this);
        modelClassArrayList = new ArrayList<>();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllContactList();
                refreshLayout.setRefreshing(false);
            }
        });
    }



    public void getAllContactList(){
        try {

            JSONArray jsonArray  = new JSONArray(String.valueOf(db.getDataFromContactMaster()));
            Log.d("MainActivity","Getting StoreMaster JSONArray "+jsonArray.toString());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Log.d("MainActivity","Getting StoreMaster Details "+jsonObject.toString());

            for (int i=0; i < jsonArray.length(); i++){
                JSONObject contactMasterObject = jsonArray.getJSONObject(i);
                Log.d("MainActivity","Printing "+contactMasterObject);

                contactMasterModel = new ContactMasterModel();

                contactMasterModel.setContactPersonName(contactMasterObject.getString("Contact_Person_Name"));
                contactMasterModel.setContactNumber(contactMasterObject.getString("Contact_Number"));

                modelClassArrayList.add(contactMasterModel);
            }
            contactAdapter = new ContactAdapter(MainActivity.this, modelClassArrayList, contactMasterModel);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(contactAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            CallingImportantMethod.showToast(MainActivity.this,"No Data Present");
        }
    }
}

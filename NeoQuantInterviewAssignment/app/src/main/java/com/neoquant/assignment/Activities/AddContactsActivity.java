package com.neoquant.assignment.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.neoquant.assignment.R;

public class AddContactsActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edt_contact_person_name, edt_contact_number;
    private Button btn_add_contact, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        initView();
    }

    public void initView(){

        edt_contact_person_name = (EditText)findViewById(R.id.edt_contact_person_name);
        edt_contact_number = (EditText)findViewById(R.id.edt_contact_number);

        btn_add_contact = (Button)findViewById(R.id.btn_add_contact);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        btn_add_contact.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btn_add_contact:
                break;

            case R.id.btn_cancel:
                break;
        }
    }

    public void addContact(){
        
    }
}

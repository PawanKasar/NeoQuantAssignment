package com.neoquant.assignment.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.neoquant.assignment.DataBaseHelper.DataBaseOpenHelper;
import com.neoquant.assignment.R;

public class AddContactsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_contact_person_name, edt_contact_number;
    private Button btn_add_contact, btn_cancel;
    private DataBaseOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        initView();
    }

    public void initView() {

        edt_contact_person_name = (EditText) findViewById(R.id.edt_contact_person_name);
        edt_contact_number = (EditText) findViewById(R.id.edt_contact_number);

        btn_add_contact = (Button) findViewById(R.id.btn_add_contact);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_add_contact.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        db = new DataBaseOpenHelper(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_add_contact:
                if (edt_contact_person_name.getText().toString().trim().isEmpty()
                        && edt_contact_number.getText().toString().trim().isEmpty()) {
                    edt_contact_person_name.setError("Please Enter Valid Value");
                    edt_contact_number.setError("Please Enter Valid Value");
                } else {
                    addContact();
                }
                break;

            case R.id.btn_cancel:
                break;
        }
    }

    public void addContact() {
        if (db.columnExists(edt_contact_number.getText().toString().trim())) {
            Toast.makeText(AddContactsActivity.this, "Data is Already Present In DataBase", Toast.LENGTH_SHORT).show();
        } else {
            db.insertIntoContactMasterTable(edt_contact_person_name.getText().toString().trim(),
                    edt_contact_number.getText().toString().trim());
            Toast.makeText(AddContactsActivity.this, "Data Inserted Successfuly", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.neoquant.assignment.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neoquant.assignment.Models.ContactMasterModel;
import com.neoquant.assignment.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.CustomeViewHolder> {

    ArrayList<ContactMasterModel> masterModelArrayList = new ArrayList<>();
    ContactMasterModel contactMasterModel = new ContactMasterModel();
    Context context;
    String contactPersonName="", contactNumber="";

    public ContactAdapter(Context context, ArrayList<ContactMasterModel> masterModelArrayList, ContactMasterModel contactMasterModel){
        this.context = context;
        this.masterModelArrayList = masterModelArrayList;
        this.contactMasterModel = contactMasterModel;
    }

    @NonNull
    @Override
    public ContactAdapter.CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlistlayout, parent, false);
        return new CustomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.CustomeViewHolder holder, int position) {
        contactMasterModel = masterModelArrayList.get(position);

        contactNumber = contactMasterModel.getContactNumber();
        Log.d("AdapterClass","Printing ContactNumber "+contactNumber);
        contactPersonName = contactMasterModel.getContactPersonName();
        Log.d("AdapterClass","Printing contactPersonName "+contactPersonName);

        holder.tv_contact_name.setText(contactPersonName);
        holder.tv_contact_number.setText(contactNumber) ;
    }

    @Override
    public int getItemCount() {
        return masterModelArrayList.size();
    }

    public class CustomeViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_contact_name, tv_contact_number;

        public CustomeViewHolder(View itemView) {
            super(itemView);

            tv_contact_name = itemView.findViewById(R.id.tv_contact_name);
            tv_contact_number = itemView.findViewById(R.id.tv_contact_number);
        }
    }
}

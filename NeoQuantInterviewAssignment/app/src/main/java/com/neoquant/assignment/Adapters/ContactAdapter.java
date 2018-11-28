package com.neoquant.assignment.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neoquant.assignment.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.CustomeViewHolder> {

    Context context;

    public ContactAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ContactAdapter.CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlistlayout, parent, false);
        return new CustomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.CustomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

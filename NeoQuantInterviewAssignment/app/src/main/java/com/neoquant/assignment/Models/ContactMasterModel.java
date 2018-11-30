package com.neoquant.assignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactMasterModel {

    @SerializedName("Contact_Number")
    @Expose
    private String contactNumber;
    @SerializedName("Contact_ID")
    @Expose
    private String contactID;
    @SerializedName("Contact_Person_Name")
    @Expose
    private String contactPersonName;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }
}

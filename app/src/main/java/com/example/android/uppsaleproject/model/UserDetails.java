package com.example.android.uppsaleproject.model;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("DisplayName")
    private String displayName;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName){this.displayName = displayName;}
}

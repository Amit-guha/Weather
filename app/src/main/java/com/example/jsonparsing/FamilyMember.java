package com.example.jsonparsing;

import com.google.gson.annotations.SerializedName;

public class FamilyMember {
    private String role;
    @SerializedName("age")
    private String mage;

    public FamilyMember(String role,String age){
        this.role=role;
        mage=age;
    }
}


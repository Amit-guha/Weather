package com.example.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson=new Gson();
        Address address=new Address("Bangladesh","Dhaka");
        List<FamilyMember>familyMembers=new ArrayList<>();
        familyMembers.add(new FamilyMember("wife","35"));
        familyMembers.add(new FamilyMember("Daughter","10"));
        Employee employee=new Employee("Amit","guha",24,"amitguho95@gmail.com",address,familyMembers);

        String jsonuser=gson.toJson(employee);
    }
}
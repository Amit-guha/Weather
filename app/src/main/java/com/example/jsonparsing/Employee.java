package com.example.jsonparsing;

import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private String mail;
    private Address address;

    private List<FamilyMember>family;


    public Employee(String firstName, String lastName, int age,String mail,Address address,List<FamilyMember>family) {
       this.firstName=firstName;
       this.lastName=lastName;
        this.age = age;
        this.mail=mail;
        this.address=address;
        this.family=family;

    }
}

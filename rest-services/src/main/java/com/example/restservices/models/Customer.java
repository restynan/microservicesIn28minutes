package com.example.restservices.models;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value ={ "password","salary"})
@JsonFilter("customerFilter")
public class Customer {


    private String userName;
  //  @JsonIgnore
    private String password;
    private double salary;

    public Customer(String userName, String password, double salary) {
        this.userName = userName;
        this.password = password;
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

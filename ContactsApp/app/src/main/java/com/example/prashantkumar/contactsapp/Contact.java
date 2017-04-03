package com.example.prashantkumar.contactsapp;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class Contact {

    int _id;
    String _name, _phoneNumber;

    public Contact(int id, String name, String phoneNumber){
        this._id = id;
        this._name = name;
        this._phoneNumber = phoneNumber;
    }
    public Contact(String name, String phoneNumber){
        this._name = name;
        this._phoneNumber = phoneNumber;
    }
    public Contact(){

    }

    public void setId(int id){
        this._id = id;
    }
    public void setName(String name){
        this._name =name;
    }
    public void setPhoneNumber(String phoneNumber){
        this._phoneNumber = phoneNumber;
    }

    public int getId(){
        return this._id;
    }
    public String getName(){
        return this._name;
    }
    public String getPhoneNumber(){
        return this._phoneNumber;
    }
}

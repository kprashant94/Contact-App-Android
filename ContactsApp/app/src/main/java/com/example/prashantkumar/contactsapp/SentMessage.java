package com.example.prashantkumar.contactsapp;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class SentMessage {
    int _id;
    String _toName, _toPhoneNumber, _message, _time;

    public SentMessage(String toName, String toPhoneNumber, String message){
        this._toName = toName;
        this._toPhoneNumber = toPhoneNumber;
        this._message = message;
    }

    public SentMessage(){

    }

    public void setId(int id){
        this._id = id;
    }
    public void setToName(String toName){
        this._toName = toName;
    }
    public void setToPhoneNumber(String toPhoneNumber){
        this._toPhoneNumber = toPhoneNumber;
    }
    public void setMessage(String message){
        this._message = message;
    }
    public void setTime(String time){
        this._time = time;
    }

    public int getId(){
        return this._id;
    }
    public String getToName(){
        return this._toName;
    }
    public String getToPhoneNumber(){
        return this._toPhoneNumber;
    }
    public String getMessage(){
        return this._message;
    }
    public String getTime(){
        return this._time;
    }
}

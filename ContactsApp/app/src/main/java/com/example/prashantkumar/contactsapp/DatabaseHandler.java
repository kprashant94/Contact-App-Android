package com.example.prashantkumar.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database name
    private static final String DATABASE_NAME = "ContactsAppDB";
    // Table names
    private static final String TABLE_CONTACTS = "Contacts";
    private static final String TABLE_SENT_MESSAGES = "SentMessages";

    //Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PHONE_NUMBER = "PhoneNumber";
    private static final String KEY_MESSAGE = "Message";
    private static final String KEY_TIME = "SentTime";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " VARCHAR(50) NOT NULL, "+
                KEY_PHONE_NUMBER + " VARCHAR(20) NOT NULL"+
                ")";
        String CREATE_TABLE_SENT_MESSAGES = "CREATE TABLE " + TABLE_SENT_MESSAGES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " VARCHAR(50) NOT NULL, "+
                KEY_PHONE_NUMBER + " VARCHAR(20) NOT NULL, "+
                KEY_MESSAGE + " VARCHAR(255) NOT NULL, "+
                KEY_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"+
                ")";
        db.execSQL(CREATE_TABLE_CONTACTS);
        db.execSQL(CREATE_TABLE_SENT_MESSAGES);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENT_MESSAGES);

        //create table again
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        if(contact.getName().isEmpty() != true | contact.getPhoneNumber().isEmpty() != true){
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_NAME, contact.getName());
            contentValues.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

            db.insert(TABLE_CONTACTS, null, contentValues);

        }
        db.close();
    }

    public void addSentMessage(SentMessage sentMessage){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, sentMessage.getToName());
        contentValues.put(KEY_PHONE_NUMBER, sentMessage.getToPhoneNumber());
        contentValues.put(KEY_MESSAGE, sentMessage.getMessage());

        db.insert(TABLE_SENT_MESSAGES, null, contentValues);
        db.close();
    }

    public List<Contact> getContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contacts = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS +
                " ORDER BY " + KEY_NAME ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)));

                contacts.add(contact);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contacts;
    }

    public List<SentMessage> getSentMessages(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<SentMessage> sentMessages = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SENT_MESSAGES +
                " ORDER BY " + KEY_TIME + " DESC" ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                SentMessage sentMessage = new SentMessage();
                sentMessage.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                sentMessage.setToName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                sentMessage.setToPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)));
                sentMessage.setMessage(cursor.getString(cursor.getColumnIndex(KEY_MESSAGE)));
                sentMessage.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));

                sentMessages.add(sentMessage);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sentMessages;
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.update(TABLE_CONTACTS, values, KEY_ID + " = ? ", new String[]{String.valueOf(contact.getId())});
        db.close();

    }

}

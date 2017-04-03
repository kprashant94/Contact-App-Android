package com.example.prashantkumar.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ContactActivity extends Activity {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PHONE_NUMBER = "PhoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        final int id = Integer.parseInt(intent.getStringExtra(KEY_ID));
        final String name = intent.getStringExtra(KEY_NAME);
        final String phoneNumber = intent.getStringExtra(KEY_PHONE_NUMBER);

        final EditText txtName = (EditText)findViewById(R.id.txtName);
        final EditText txtPhoneNumber = (EditText)findViewById((R.id.txtPhoneNumber));

        txtName.setText(name);
        txtPhoneNumber.setText(phoneNumber);

        Button buttonDelete = (Button)findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(view.getContext());
                db.deleteContact(id);
                db.close();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Button buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(view.getContext());
                db.updateContact(new Contact(id, txtName.getText().toString(), txtPhoneNumber.getText().toString()));
                db.close();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Button buttonSendMessage = (Button)findViewById(R.id.buttonSend);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ComposeMessageActivity.class);
                intent.putExtra(KEY_ID, Integer.toString(id));
                intent.putExtra(KEY_NAME, name);
                intent.putExtra(KEY_PHONE_NUMBER, phoneNumber);
                startActivity(intent);
            }
        });
    }
}

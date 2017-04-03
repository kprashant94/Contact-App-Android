package com.example.prashantkumar.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddNewContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        final EditText txtName = (EditText)findViewById(R.id.txtName);
        final EditText txtPhoneNumber = (EditText)findViewById(R.id.txtPhoneNumber);
        Button buttonAddContact = (Button)findViewById(R.id.buttonDelete);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(view.getContext());
                db.addContact(new Contact(txtName.getText().toString(), txtPhoneNumber.getText().toString()));
                db.close();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

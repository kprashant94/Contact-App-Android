package com.example.prashantkumar.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class ContactsListAdapter extends ArrayAdapter<Contact> {

    public ContactsListAdapter(Context context, List<Contact> contacts){
        super(context, R.layout.contact_row, contacts);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.contact_row, parent, false);

        final Contact contact = getItem(position);
        TextView txtId = (TextView)view.findViewById(R.id.txtId);
        TextView txtName = (TextView)view.findViewById(R.id.txtName);
        TextView txtPhoneNumber = (TextView)view.findViewById(R.id.txtPhoneNumber);

        txtId.setText(Integer.toString(contact.getId()));
        txtName.setText(contact.getName());
        txtPhoneNumber.setText(contact.getPhoneNumber());

        return view;

    }
}

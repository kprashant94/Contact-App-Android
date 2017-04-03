package com.example.prashantkumar.contactsapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class ContactsTabFragment extends Fragment {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PHONE_NUMBER = "PhoneNumber";

    List<Contact> contactsList = new ArrayList<>();

    public ContactsTabFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_contacts_tab, container, false);

        Button buttonAddContact = (Button)view.findViewById(R.id.buttonDelete);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNewContactActivity.class);
                startActivity(intent);
            }
        });

        loadContacts();
        ListView listView = (ListView)view.findViewById(R.id.list);
        ListAdapter listAdapter = new ContactsListAdapter(getActivity(), contactsList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtId = (TextView)view.findViewById(R.id.txtId);
                TextView txtName = (TextView)view.findViewById(R.id.txtName);
                TextView txtPhoneNumber = (TextView)view.findViewById(R.id.txtPhoneNumber);

                Intent intent = new Intent(getActivity(), ContactActivity.class);
                intent.putExtra(KEY_ID, txtId.getText().toString());
                intent.putExtra(KEY_NAME, txtName.getText().toString());
                intent.putExtra(KEY_PHONE_NUMBER, txtPhoneNumber.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }

    public void loadContacts(){
        DatabaseHandler db = new DatabaseHandler(getActivity());
        List<Contact> contacts = db.getContacts();

        for (Contact contact : contacts){
            contactsList.add(contact);
        }

    }
}

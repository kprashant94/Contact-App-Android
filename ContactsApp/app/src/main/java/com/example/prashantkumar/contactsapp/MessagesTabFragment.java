package com.example.prashantkumar.contactsapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class MessagesTabFragment extends Fragment {
    List<SentMessage> sentMessagesList = new ArrayList<>();

    public MessagesTabFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_messages_tab, container, false);

        loadSentMessages();

        ListView listView = (ListView)view.findViewById(R.id.list);
        ListAdapter listAdapter = new SentMessagesListAdapter(view.getContext(), sentMessagesList);
        listView.setAdapter(listAdapter);

        return view;
    }

    public void loadSentMessages(){
        DatabaseHandler db = new DatabaseHandler(getActivity());
        List<SentMessage> sentMessages = db.getSentMessages();
        for(SentMessage sentMessage : sentMessages){
            sentMessagesList.add(sentMessage);
        }
    }
}

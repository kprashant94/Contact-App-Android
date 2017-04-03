package com.example.prashantkumar.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PrashantKumar on 4/2/2017.
 */
public class SentMessagesListAdapter extends ArrayAdapter<SentMessage> {
    public SentMessagesListAdapter(Context context, List<SentMessage> sentMessages){
        super(context, R.layout.row_sent_message, sentMessages);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.row_sent_message, parent, false);

        final SentMessage sentMessage = getItem(position);
        TextView txtName = (TextView)view.findViewById(R.id.txtName);
        TextView txtOtp = (TextView)view.findViewById(R.id.txtOtp);
        TextView txtTime = (TextView)view.findViewById(R.id.txtTime);

        txtName.setText(sentMessage.getToName());
        txtOtp.setText(sentMessage.getMessage());
        txtTime.setText(sentMessage.getTime());

        return view;

    }
}

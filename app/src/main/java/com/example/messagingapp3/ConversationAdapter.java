package com.example.messagingapp3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends ArrayAdapter<String> {

    private List<String> Name = new ArrayList<>();
    Context context;


    public ConversationAdapter(@NonNull Activity context, List<String> Name) {
        super(context, R.layout.conversation_layout, Name);
        this.context = context;
        this.Name = Name;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        String Pname = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.conversation_layout, parent, false);
        }

        final ListView lst = (ListView) convertView.findViewById(R.id.listview);

        TextView text = (TextView) convertView.findViewById(R.id.PersonName);
        text.setText(Pname);

        return convertView;
    }
}

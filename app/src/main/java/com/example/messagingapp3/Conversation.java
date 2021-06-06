package com.example.messagingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class Conversation extends AppCompatActivity {

    String NamePerson;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    DatabaseReference myReference;
    List<String> data = new ArrayList<>();
    ListView listview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_page);
        NamePerson = getIntent().getStringExtra("Person");

        Toast.makeText(getApplicationContext(), NamePerson, Toast.LENGTH_SHORT).show();

        //create child "Hammad" "Abdullah" for example
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child(NamePerson);

        listview = (ListView) findViewById(R.id.listview);
        if (NamePerson.equals("Hammad")) {
            data.add("Abdullah");
            ConversationAdapter adapter = new ConversationAdapter(this, data);
            listview.setAdapter(adapter);
        } else {
            data.add("Hammad");
            ConversationAdapter adapter = new ConversationAdapter(this, data);
            listview.setAdapter(adapter);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemValue = (String) listview.getItemAtPosition(position);
                String pos = String.valueOf(position + 1);
                String n = data.get(position);

                //call another activity messaging interface using intent
                Intent intent = new Intent(getApplicationContext(), MainMessage.class);
                intent.putExtra("Id", pos);
                intent.putExtra("name", n);
                intent.putExtra("PersonDevice",NamePerson);
                startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

            }
        }
    }




}

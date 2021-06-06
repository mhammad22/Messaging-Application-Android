package com.example.messagingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainMessage extends AppCompatActivity {

    String Name;
    int Id;
    String DevicePerson;
    EditText Msg;
    Button Send;
    String Message;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    DatabaseReference myRef1;
    DatabaseReference myReference;
    DatabaseReference myReference1;
    ChatMessage obj, object;
    List<ChatMessage> store = new ArrayList<>();
    String DraftTime;
    String DraftMessage = null;
    boolean flag=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_message);

        final RecyclerView ListMessage = (RecyclerView) findViewById(R.id.ListMessage);

        ListMessage.setLayoutManager(new LinearLayoutManager(this));

        Msg = (EditText) findViewById(R.id.edittext_chatbox);
        Send = (Button) findViewById(R.id.button_chatbox_send);

        Name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("Id");
        Id = Integer.valueOf(id);
        DevicePerson = getIntent().getStringExtra("PersonDevice");
        setTitle(Name);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child(DevicePerson).child(Name);
        myRef.keepSynced(true);
        myRef1 = mFirebaseDatabase.getReference().child(DevicePerson).child(Name);
        myRef1.keepSynced(true);
        myReference = mFirebaseDatabase.getReference().child(Name).child(DevicePerson);
        myReference.keepSynced(true);


        //check draft message in database, Remove and Display
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (flag) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ChatMessage MsgObj = new ChatMessage();
                        MsgObj = ds.getValue(ChatMessage.class);
                        if (MsgObj.getType().equals("Draft")) {
                            Toast.makeText(getApplicationContext(), "Draft Found", Toast.LENGTH_SHORT).show();
                            Msg.setText(MsgObj.getMessage());
                            ds.getRef().removeValue();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //get previous store messages from database
        myRef1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                store.clear();
                ListMessage.setAdapter(new MessageAdapter(store));
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ChatMessage MsgObj = new ChatMessage();
                    MsgObj = ds.getValue(ChatMessage.class);
                    if(!MsgObj.getType().contentEquals("Draft")) {
                        store.add(MsgObj);
                    }
                    //Toast.makeText(getApplicationContext(), MsgObj.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //get text from edit text and save it into database against respected user
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get Message and Save in Child under Hammad with
                Date currentTime = Calendar.getInstance().getTime();
                String TimeP = java.text.DateFormat.getTimeInstance().format(new Date());
                obj = new ChatMessage();
                Message = Msg.getText().toString();
                obj.setMessage(Message);
                Msg.setText("");
                obj.setName(DevicePerson);
                obj.setTime(TimeP);
                obj.setType("Send");
                obj.setReceiverName(Name);
                myRef.push().setValue(obj);
                Toast.makeText(getApplicationContext(), "Send", Toast.LENGTH_SHORT).show();

                object = new ChatMessage(Message, DevicePerson, TimeP, "Receive", Name);
                myReference.getRef().push().setValue(object);

            }
        });

        ListMessage.setAdapter(new MessageAdapter(store));

    }

    //On back pressed . Saved Draft message in Database
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        String d = Msg.getText().toString();
        DraftTime = java.text.DateFormat.getTimeInstance().format(new Date());

        if (!d.isEmpty()) {
            ChatMessage Draft = new ChatMessage();
            Draft.setMessage(d);
            Draft.setType("Draft");
            Draft.setName(DevicePerson);
            Draft.setReceiverName(Name);
            Draft.setTime(DraftTime);
            myRef.push().setValue(Draft);
            Toast.makeText(getApplicationContext(), "Draft", Toast.LENGTH_SHORT).show();
            flag=false;
        }
        super.onBackPressed();
    }


}

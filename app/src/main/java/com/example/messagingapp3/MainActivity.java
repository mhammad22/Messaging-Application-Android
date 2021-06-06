package com.example.messagingapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    String PersonDevice=null;
    EditText Name;
    Button btn;
    String Message = "";
    String path = null;
    boolean flag = true;
    String FILE_NAME = "name";
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder stringBuilder1 = new StringBuilder();
    InputStream inputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = getApplicationContext().getFilesDir().getPath();
        Toast.makeText(getApplicationContext(),path,Toast.LENGTH_SHORT).show();
        File file = new File(path, FILE_NAME);
        if (!file.exists()) {
            Toast.makeText(getApplicationContext(), "File Not Exist", Toast.LENGTH_LONG).show();
            flag = false;
        } else {
            Toast.makeText(getApplicationContext(), "File Exist", Toast.LENGTH_LONG).show();
            flag = true;
        }

        //if flag true means file already exist read data and move to next activity
        if (flag == true) {
            try {
                int read;
                inputStream = openFileInput(FILE_NAME);
                while ((read = inputStream.read()) != -1) {
                    stringBuilder.append((char) read);
                }
                //contain already exist name from file
                PersonDevice = stringBuilder.toString();
                Intent intent = new Intent(getApplicationContext(),Conversation.class);
                intent.putExtra("Person",PersonDevice);
                startActivity(intent);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        Name = (EditText) findViewById(R.id.TextViewN);
        btn = (Button) findViewById(R.id.ButtonN);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message = Name.getText().toString();
                try {
                    FileOutputStream outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    outputStream.write(Message.getBytes());
                    Intent intent = new Intent(getApplicationContext(),Conversation.class);
                    intent.putExtra("Person",Message);
                    startActivity(intent);

                    outputStream.flush();
                } catch (FileNotFoundException e) //file not found
                {
                    e.printStackTrace();
                } catch (IOException e) // write exception
                {
                    e.printStackTrace();
                }

            }
        });



    }
}
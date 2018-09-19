package com.therabot.christopherluey.therabot;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveOpenMenu extends AppCompatActivity {
    static String namefile;
    File file;
    static String filename = "TheraBot " + TimeDate.getCreatedAt();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_open_menu);


        final Context context = getApplicationContext();
        final File path = context.getExternalFilesDir(null);

        final Button save = findViewById(R.id.Save);
        final Button open = findViewById(R.id.Open);
        final TextView showtext =  findViewById(R.id.showtext);
        final EditText name = findViewById(R.id.name);

        //on editor listener
        name.setHint(filename);
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (name.getText().toString().equals("")){
                        Log.d("namehint", String.valueOf(name.getHint()));
                        namefile = String.valueOf(name.getHint());
                        return false;
                    } else{
                        Log.d("namestring", String.valueOf(name.getText().toString()));
                        namefile = String.valueOf(name.getText().toString());
                    }
                    name.setText(namefile);
                }
                return false;
            }
        });

        //save file button onClick listener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read(path, namefile);
            }
        });

        //open file browser
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String read = ReadWrite.read(file);
                read.replaceAll("[|]", "");
                showtext.setText(read);
                List<String> readlist = new ArrayList<>(Arrays.asList(read.split(",")));
                MainActivity.listMessage = readlist;
                Log.d("listmessage", String.valueOf(MainActivity.listMessage));
            }
        });

    }
    public void read(File path, String filename) {
        Log.d("clicked", "onClick: ");
        Log.d("path", String.valueOf(path));
        Log.d("namefile", String.valueOf(filename));

        if (filename == null) {
            filename = SaveOpenMenu.filename;
            Log.d("namefile", String.valueOf(filename));
        }

        File file = new File(path, filename + ".txt");

        try{
            ReadWrite.write(file);
            Toast.makeText(SaveOpenMenu.this, "Saved to files!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(SaveOpenMenu.this, "Failed to save to files.", Toast.LENGTH_SHORT).show();
        }
    }

}


package com.therabot.christopherluey.therabot;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.util.Log.d;

public class SaveOpenMenu extends AppCompatActivity {
    static String namefile;
    File file;
    static String filename = "TheraBot " + TimeDate.getCreatedAt();
    //String[] fileArray = fileList();
    static RecyclerView recycler;
    static SaveOpenAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_open_menu);

        ArrayList<SaveOpenTypes> list = new ArrayList<>();
        list.add(new SaveOpenTypes(SaveOpenTypes.SAVE, R.layout.savecurrent));
        list.add(new SaveOpenTypes(SaveOpenTypes.OPEN, R.layout.openprevious));

        recycler = findViewById(R.id.recycler2);
        recycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SaveOpenAdapter(list, this);
        recycler.setAdapter(mAdapter);


//        final Context context = getApplicationContext();
//        Context context = getApplicationContext();
//        final File path = context.getExternalFilesDir(null);

        //final Button save = findViewById(R.id.Save);
        //final Button open = findViewById(R.id.Open);
        //final TextView showtext =  findViewById(R.id.showtext);
        //final EditText name = findViewById(R.id.name);
//        ListView fileList = findViewById(R.id.fileList);
//        String[] dislist = path.list();
//       ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_open_list_view, dislist);
//        fileList.setAdapter(adapter);
//
//        fileList.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//
//        //on editor listener
//        name.setHint(filename);
//        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    if (name.getText().toString().equals("")){
//                        d("namehint", String.valueOf(name.getHint()));
//                        namefile = String.valueOf(name.getHint());
//                        return false;
//                    } else{
//                        d("namestring", String.valueOf(name.getText().toString()));
//                        namefile = String.valueOf(name.getText().toString());
//                    }
//                    name.setText(namefile);
//                }
//                return false;
//            }
//        });
//
//        //save file button onClick listener
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                read(path, namefile);
//            }
//        });
//
//        Log.d("filearray", String.valueOf(fileList()));
//
//        //open file browser
////        open.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                String read = ReadWrite.read(file);
////                read.replaceAll("[|]", "");
////                //showtext.setText(read);
////                List<String> readlist = new ArrayList<>(Arrays.asList(read.split(",")));
////                MainActivity.listMessage = readlist;
////                d("listmessage", String.valueOf(MainActivity.listMessage));
////            }
////        });
//
//    }
//    public void read(File path, String filename) {
//        d("clicked", "onClick: ");
//        d("path", String.valueOf(path));
//        d("namefile", String.valueOf(filename));
//
//        if (filename == null) {
//            filename = SaveOpenMenu.filename;
//            d("namefile", String.valueOf(filename));
//        }
//
//        File file = new File(path, filename + ".txt");
//
//        try{
//            ReadWrite.write(file);
//            Toast.makeText(SaveOpenMenu.this, "Saved to files!", Toast.LENGTH_SHORT).show();
//        } catch (Exception e){
//            Toast.makeText(SaveOpenMenu.this, "Failed to save to files.", Toast.LENGTH_SHORT).show();
//        }
    }

}


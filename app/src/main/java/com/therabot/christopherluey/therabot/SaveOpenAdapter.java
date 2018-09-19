package com.therabot.christopherluey.therabot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.util.Log.d;

/**
 * Created by christopherluey on 9/18/18.
 */

public class SaveOpenAdapter extends RecyclerView.Adapter{

    private ArrayList<SaveOpenTypes> mData;
    int total_types;
    Context context;

    static String filename = "TheraBot " + TimeDate.getCreatedAt();
    static String namefile;
    String[] dislist;
    ArrayAdapter adapter;
    
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            case SaveOpenTypes.SAVE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.savecurrent, parent, false);
                return new Save(view);
            case SaveOpenTypes.OPEN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.openprevious, parent, false);
                return new Open(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        SaveOpenTypes object = mData.get(position);
        if (object != null) {
            switch (object.type) {
                case SaveOpenTypes.SAVE:
                    final File path = context.getExternalFilesDir(null);
                    ((Save) holder).name.setHint(filename);
                    ((Save) holder).name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                if (((Save) holder).name.getText().toString().equals("")){
                                    namefile = String.valueOf(((Save) holder).name.getHint());
                                    return false;
                                } else{
                                    namefile = String.valueOf(((Save) holder).name.getText().toString());
                                }
                                ((Save) holder).name.setText(namefile);
                            }
                            return false;
                        }
                    });

                    //save file button onClick listener
                    ((Save) holder).save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (filename == null) {
                                filename = SaveOpenMenu.filename;
                            }

                            File file = new File(path, filename + ".txt");

                            try{
                                ReadWrite.write(file);
                                Toast.makeText(context, "Saved to files!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e){
                                Toast.makeText(context, "Failed to save to files.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;

                case SaveOpenTypes.OPEN:
                    dislist = context.getExternalFilesDir(null).list();
                    if (dislist.length < 0){
                        dislist[0] = "No Available Files to Open. Please Save a File Before Opening";
                    }
                    Log.d("dislist", String.valueOf(dislist.length));
                    adapter = new ArrayAdapter<String>(context, R.layout.activity_open_list_view, R.id.textView9, dislist);
                    ((Open) holder).fileList.setAdapter(adapter);
                    ((Open) holder).refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dislist = context.getExternalFilesDir(null).list();
                            adapter.notifyDataSetChanged();
                            ((Open) holder).fileList.setAdapter(adapter);
                            for (int i = 0; i <= SaveOpenMenu.mAdapter.getItemCount(); i++) {
                                SaveOpenMenu.mAdapter.notifyItemChanged(i);
                            }
                        }
                    });

                    ((Open) holder).fileList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                            ((Open) holder).fileList.getSelectedItem();
                            Log.d("filelist", String.valueOf(((Open) holder).fileList.getSelectedItem()));
                            //((Open) holder).fileList.getItemAtPosition(position);
                            //String read = ReadWrite.read(file);
                            //read.replaceAll("[|]", "");
                            //showtext.setText(read);
                            //List<String> readlist = new ArrayList<>(Arrays.asList(read.split(",")));
                            //MainActivity.listMessage = readlist;
                            d("listmessage", String.valueOf(MainActivity.listMessage));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            Log.d("nothing", "onNothingSelected: ");
                }
            });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (mData.get(position).type) {
            case 0:
                return SaveOpenTypes.SAVE;
            case 1:
                return SaveOpenTypes.OPEN;
            default:
                return -1;
        }
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class Save extends RecyclerView.ViewHolder{
        Button save;
        EditText name;

        public Save (View itemView) {
            super(itemView);

            this.save = (Button) itemView.findViewById(R.id.save);
            this.name = itemView.findViewById(R.id.name);
        }
    }

    public static class Open extends RecyclerView.ViewHolder{
        ListView fileList;
        Button refresh;

        public Open (View itemView) {
            super(itemView);

            this.fileList = itemView.findViewById(R.id.fileList);
            this.refresh = itemView.findViewById(R.id.refresh);
        }
    }

    public SaveOpenAdapter (ArrayList<SaveOpenTypes>data, Context context){
        this.mData = data;
        this.context = context;
        total_types = mData.size();
    }
}

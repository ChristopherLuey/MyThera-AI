package com.therabot.christopherluey.therabot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by christopherluey on 9/18/18.
 * RecyclerView Adapter for Save Open class
 */

public class SaveOpenAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private ArrayList<SaveOpenTypes> mData;
    Context context;
    int total_types;

    static String filename = "TheraBot " + TimeDate.getCreatedAt();
    static String namefile;
    String[] dislist;
    ArrayAdapter adapter;
    File filepath;

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
                            filepath = new File(file.getPath());

                            try{
                                ReadWrite.write(file, context);
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

                    ((Open) holder).fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String filepathstring = String.valueOf(context.getExternalFilesDir(null)) + "/" +
                                    String.valueOf(((Open) holder).fileList.getItemAtPosition(position));

                            File file = new File(filepathstring);

                            String read = ReadWrite.read(file, context);
                            read.replaceAll("[|]", "");
                            List<String> readlist;
                            try {
                                readlist = new ArrayList<>(Arrays.asList(read.split("#")));
                            } catch (Exception e){
                                readlist = new ArrayList<>();
                            }
                            if (MainActivity.listMessage != null) {
                                MainActivity.listMessage.clear();
                                MainActivity.mMessageRecycler.removeAllViewsInLayout();
                            }
                            if(readlist.size() != 1) {
                                if(readlist.size() != 0) {
                                    for (int i = 1; i <= readlist.size(); i++) {
                                        MainActivity.listMessage.add(readlist.get(i-1));
                                        MainActivity.mMessageAdapter.notifyDataSetChanged();
                                    }
                                }
                            } else {
                                MainActivity.listMessage.add(readlist.get(0));
                                MainActivity.mMessageAdapter.notifyDataSetChanged();
                            }

                            for (int i =0; i<=MainActivity.listMessage.size(); i++) {
                                MainActivity.mMessageAdapter.notifyItemChanged(i);
                                Toast toast = Toast.makeText(context, "Successfully Opened!", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }
                    });
                    break;

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

    @Override
    public void onClick(View v) {

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

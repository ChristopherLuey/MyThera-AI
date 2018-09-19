package com.therabot.christopherluey.therabot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SaveOpenMenu extends AppCompatActivity {

    static String filename = "TheraBot " + TimeDate.getCreatedAt();
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

    }

}


package com.therabot.christopherluey.therabot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ArrayList<SettingsTypes> list = new ArrayList<>();
        list.add(new SettingsTypes(SettingsTypes.HIDE_MESSAGE_SENT, R.layout.hidemessagesenttime));
        list.add(new SettingsTypes(SettingsTypes.HIDE_BOT_ICON, R.layout.hideboticon));
        list.add(new SettingsTypes(SettingsTypes.CHANGE_TEXT, R.layout.changetextsize));
        list.add(new SettingsTypes(SettingsTypes.ABOUT, R.layout.about));
        list.add(new SettingsTypes(SettingsTypes.FEEDBACK, R.layout.feedback));
        list.add(new SettingsTypes(SettingsTypes.CLEAR, R.layout.cleartrans));
        list.add(new SettingsTypes(SettingsTypes.SAVEOPEN, R.layout.saveopen));
        //list.add(new SettingsTypes(SettingsTypes.RESET, R.layout.resetset));
        list.add(new SettingsTypes(SettingsTypes.TEXT, R.layout.therabotby));


        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        SettingsAdapter mAdapter = new SettingsAdapter(list, this);
        recycler.setAdapter(mAdapter);
    }
}

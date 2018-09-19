package com.therabot.christopherluey.therabot;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


import java.util.ArrayList;


/**
 * Created by christopherluey on 9/16/18.
 */

public class SettingsAdapter extends RecyclerView.Adapter {

    Boolean showmessagetime = MainActivity.showmessagetimemain;
    Boolean iconbothide = MainActivity.iconbothidemain;
    private ArrayList<SettingsTypes> mData;
    Context context;
    int total_types;
    static int textSizenumber;

    //Switch hide sent message time
    public static class HideMessageSent extends RecyclerView.ViewHolder{

        Switch hidesent;

        public HideMessageSent(View itemView) {
            super(itemView);

            this.hidesent = (Switch) itemView.findViewById(R.id.messageSentTime2);
        }
    }

    //Wwitch hide bot icon
    public static class hideboticon extends RecyclerView.ViewHolder{

        Switch hideboticon;

        public hideboticon(View itemView) {
            super(itemView);

            this.hideboticon = (Switch) itemView.findViewById(R.id.showBotIcon);
        }
    }

    //Change text size seekbar
    public static class changetext extends RecyclerView.ViewHolder{

        TextView random;
        TextView textSizeis;
        SeekBar changeText;
        TextView doesnotmatter;

        public changetext(View itemView) {
            super(itemView);

            this.random = itemView.findViewById(R.id.textView3);
            this.textSizeis = itemView.findViewById(R.id.textSizeIs2);
            this.changeText = itemView.findViewById(R.id.textSize2);
            this.doesnotmatter = itemView.findViewById(R.id.changeTextText2);

        }
    }

    //About button
    public static class aboutbutton extends RecyclerView.ViewHolder{

        Button about;


        public aboutbutton(View itemView) {
            super(itemView);

            this.about = itemView.findViewById(R.id.about2);

        }
    }

    //Feedback button
    public static class feedback extends RecyclerView.ViewHolder{

        Button feedback;

        public feedback(View itemView) {
            super(itemView);

            this.feedback = itemView.findViewById(R.id.feedback);

        }
    }

    //Clear transcript button
    public static class clear extends RecyclerView.ViewHolder{

        Button clear;
        public clear(View itemView) {
            super(itemView);

            this.clear = itemView.findViewById(R.id.clearTranscript2);
        }
    }

    //Open save or open previous transcript menu
    public static class saveopen extends RecyclerView.ViewHolder{

        Button saveopen;
        public saveopen(View itemView) {
            super(itemView);

            this.saveopen = itemView.findViewById(R.id.saveandopen2);
        }
    }

    //Reset settings button. Doesn't quite work yet.
//    public static class reset extends RecyclerView.ViewHolder{
//
//        Button reset;
//        public reset(View itemView) {
//            super(itemView);
//
//            this.reset = itemView.findViewById(R.id.resetSettings);
//        }
//    }

    //TextView TheraBot - By: Christopher Luey
    public static class byme extends RecyclerView.ViewHolder{

        TextView by;
        public byme (View itemView) {
            super(itemView);

            this.by = itemView.findViewById(R.id.by2);
        }
    }

    //SettingsAdapter function called in Settings Activity
    public SettingsAdapter (ArrayList<SettingsTypes>data, Context context){
        this.mData = data;
        this.context = context;
        total_types = mData.size();
    }

    //RecyclerView holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case SettingsTypes.HIDE_MESSAGE_SENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hidemessagesenttime, parent, false);
                return new HideMessageSent(view);
            case SettingsTypes.HIDE_BOT_ICON:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hideboticon, parent, false);
                return new hideboticon(view);
            case SettingsTypes.CHANGE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.changetextsize, parent, false);
                return new changetext(view);
            case SettingsTypes.ABOUT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about, parent, false);
                return new aboutbutton(view);
            case SettingsTypes.FEEDBACK:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback, parent, false);
                return new feedback(view);
            case SettingsTypes.CLEAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cleartrans, parent, false);
                return new clear(view);
            case SettingsTypes.SAVEOPEN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saveopen, parent, false);
                return new saveopen(view);
//            case SettingsTypes.RESET:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resetset, parent, false);
//                return new reset(view);
            case SettingsTypes.TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.therabotby, parent, false);
                return new byme(view);
        }
        return null;
    }

    //Binding data to views
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPostition) {
        SettingsTypes object = mData.get(listPostition);

        if (object != null) {
            switch (object.type) {

                //Binding hide message sent time
                case SettingsTypes.HIDE_MESSAGE_SENT:
                    ((HideMessageSent) holder).hidesent.setChecked(showmessagetime);
                    ((HideMessageSent) holder).hidesent.setTextOn("Hidden");
                    ((HideMessageSent) holder).hidesent.setTextOn("Shown");

                    //Setting the switch OnCheckedListener
                    ((HideMessageSent) holder).hidesent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Log.d("showmessagetime", String.valueOf(showmessagetime));

                            if (isChecked) {
                                showmessagetime = true;
                                MainActivity.showmessagetimemain = showmessagetime;
                                Log.d("showmessagetime", String.valueOf(showmessagetime));
                                for (int i = 0; i <= MainActivity.mMessageAdapter.getItemCount(); i++){
                                    MainActivity.mMessageAdapter.notifyItemChanged(i);
                                }

                            } else {
                                showmessagetime = false;
                                MainActivity.showmessagetimemain = showmessagetime;
                                Log.d("showmessagetime", String.valueOf(showmessagetime));
                                for (int i = 0; i <= MainActivity.mMessageAdapter.getItemCount(); i++){
                                    MainActivity.mMessageAdapter.notifyItemChanged(i);
                                }

                            }
                        }
                    });
                    break;

                //Binding hide bot icon
                case SettingsTypes.HIDE_BOT_ICON:
                    ((hideboticon) holder).hideboticon.setChecked(iconbothide);
                    ((hideboticon) holder).hideboticon.setTextOn("Hidden");
                    ((hideboticon) holder).hideboticon.setTextOff("Shown");
                    ((hideboticon) holder).hideboticon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked){
                                iconbothide = true;
                                MainActivity.iconbothidemain = iconbothide;
                                for (int i = 0; i <= MainActivity.mMessageAdapter.getItemCount(); i++){
                                    MainActivity.mMessageAdapter.notifyItemChanged(i);
                                }

                            } else {
                                iconbothide = false;
                                MainActivity.iconbothidemain = iconbothide;
                                for (int i = 0; i <= MainActivity.mMessageAdapter.getItemCount(); i++){
                                    MainActivity.mMessageAdapter.notifyItemChanged(i);
                                }
                            }
                        }
                    });
                    break;

                //Binding change text size attributes
                case SettingsTypes.CHANGE_TEXT:
                    ((changetext) holder).changeText.setProgress(MainActivity.textsizenumbermain);
                    ((changetext) holder).changeText.setMax(20);
                    ((changetext) holder).changeText.incrementProgressBy(1);

                    int number = MainActivity.textsizenumbermain + 12;
                    ((changetext) holder).textSizeis.setText("Text Size is: " + number);


                    //Seekbar onChangeListener
                    ((changetext) holder).changeText.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            textSizenumber = progress;
                            MainActivity.textsizenumbermain = textSizenumber;

                            int number = textSizenumber + 12;
                            ((changetext) holder).textSizeis.setText("Text Size is: " + number);

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            int number = MainActivity.textsizenumbermain + 12;
                            ((changetext) holder).textSizeis.setText("Text Size is: " + number);
                            for (int i = 0; i <= MainActivity.mMessageAdapter.getItemCount(); i++){
                                MainActivity.mMessageAdapter.notifyItemChanged(i);
                            }                        }
                    });
                    break;

                //Binding about button
                case SettingsTypes.ABOUT:
                    ((aboutbutton) holder).about.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //start new activity with about                            Context mcontext = v.getContext();
                            Context mcontext = v.getContext();
                            Intent intent = new Intent(mcontext, aboutpage.class);
                            mcontext.startActivity(intent);

                        }
                    });
                    break;

                //Binding feedback button
                case SettingsTypes.FEEDBACK:
                    ((feedback) holder).feedback.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Context mcontext = v.getContext();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse("https://goo.gl/forms/KT04z31kzCFVfEf42"));
                            //Calling intent to open website to feedback survey
                            mcontext.startActivity(intent);
                        }
                    });
                    break;

                //Binding clear transcript button
                case SettingsTypes.CLEAR:
                    ((clear) holder).clear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.mMessageRecycler.removeAllViewsInLayout();
                            MainActivity.listMessage.clear();
                            MainActivity.mMessageAdapter.notifyDataSetChanged();

                        }
                    });
                    break;

                //Binding button that opens to saveopen activity
                case SettingsTypes.SAVEOPEN:
                    ((saveopen) holder).saveopen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Context mcontext = v.getContext();
                            //start new activity with open and save options
                            Intent intent = new Intent(mcontext, SaveOpenMenu.class);
                            mcontext.startActivity(intent);
                        }
                    });
                    break;

                //Binding reset button. Doesn't quite work.
//                case SettingsTypes.RESET:
//                    ((reset) holder).reset.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Log.d("reset", "onClick: ");
//                            MainActivity.textsizenumbermain = 6;
//                            textSizenumber = 6;
//                            int awesomenumber = MainActivity.textsizenumbermain + 12;
//                            ((changetext) holder).textSizeis.setText("Text Size is: " + awesomenumber);
//                            ((changetext) holder).changeText.setProgress((MainActivity.textsizenumbermain) + 1);
//                            showmessagetime = false;
//                            MainActivity.showmessagetimemain = false;
//                            ((HideMessageSent) holder).hidesent.setChecked(showmessagetime);
//                            iconbothide = false;
//                            MainActivity.iconbothidemain = false;
//                            ((hideboticon) holder).hideboticon.setChecked(iconbothide);
//
//                        }
//                    });
//                    break;
            }
        }
    }

    //Reading data from list
    @Override
    public int getItemViewType(int position) {

        switch (mData.get(position).type) {
            case 0:
                return SettingsTypes.HIDE_MESSAGE_SENT;
            case 1:
                return SettingsTypes.HIDE_BOT_ICON;
            case 2:
                return SettingsTypes.CHANGE_TEXT;
            case 3:
                return SettingsTypes.ABOUT;
            case 4:
                return SettingsTypes.FEEDBACK;
            case 5:
                return SettingsTypes.CLEAR;
            case 6:
                return SettingsTypes.SAVEOPEN;
//            case 7:
//                return SettingsTypes.RESET;
            case 8:
                return SettingsTypes.TEXT;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

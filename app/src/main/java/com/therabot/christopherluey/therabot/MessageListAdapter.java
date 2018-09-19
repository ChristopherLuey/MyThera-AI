package com.therabot.christopherluey.therabot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by christopherluey on 8/2/18.
 * RecyclerView Adapter for the MainActivity message UI
 */


public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List mMessageList;


    MessageListAdapter(List messageList) {
        //Called by MainActivity.class
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        //Called by the RecyclerView
        return mMessageList.size();
    }

    public int getItemViewType(int position) {
        String message = String.valueOf(mMessageList.get(position));

        if (String.valueOf(message.charAt(0)).equals("0")) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            //RecyclerView Adapter inflating view for message sent
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            //RecyclerView Adapter inflating view for message received
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Binding messages to views
        String message = String.valueOf(mMessageList.get(position));

        switch (getItemViewType(position)) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);

        }

        void bind(String message) {
            //Editing Text Size based on the settings
            messageText.setTextSize(MainActivity.textsizenumbermain + 12);

            //Editing time sent visibility based on the settings
            if (MainActivity.showmessagetimemain) {
                timeText.setVisibility(View.INVISIBLE);
            } else {
                timeText.setVisibility(View.VISIBLE);
            }

            messageText.setText(message.substring(2));

            //Format the stored timestamp into a readable String using method.
            timeText.setText(String.valueOf(TimeDate.getCreatedAt()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.text_message_received);
            timeText = itemView.findViewById(R.id.text_message_received_time);
            nameText = itemView.findViewById(R.id.text_message_received_name);
            profileImage = itemView.findViewById(R.id.text_message_received_profile_pic);


        }

        void bind(String message) {
            messageText.setTextSize(MainActivity.textsizenumbermain + 12);

            if (MainActivity.showmessagetimemain) {
                timeText.setVisibility(View.INVISIBLE);
            } else {
                timeText.setVisibility(View.VISIBLE);
            }

            if (MainActivity.iconbothidemain) {
                profileImage.setVisibility(View.INVISIBLE);
            } else {
                profileImage.setVisibility(View.VISIBLE);

            }

            messageText.setText(message.substring(2));

            // Format the stored timestamp into a readable String using method.
            timeText.setText(String.valueOf(TimeDate.getCreatedAt()));

            nameText.setText("TheraBot");

        }
    }
}
package com.therabot.christopherluey.therabot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by christopherluey on 8/2/18.
 */


public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List mMessageList;


    MessageListAdapter(List messageList) {
        Log.d("done", "MessageListAdapter: ");
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        Log.d("completed", "getItemCount: ");
        Log.d("completed", String.valueOf(mMessageList));
        Log.d("completed", String.valueOf(mMessageList.size()));
        return mMessageList.size();
    }

    public int getItemViewType(int position) {
        String message = String.valueOf(mMessageList.get(position));
        Log.d("message", String.valueOf(message));

        if (String.valueOf(message.charAt(0)).equals("0")) {
            // If the current user is the sender of the message
            Log.d("works", "getItemViewType: ");
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            Log.d("does this", "getItemViewType: ");
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("oncreate", "onCreateViewHolder: ");
        View view;
        Log.d("ViewType", String.valueOf(viewType));
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            Log.d("sent", "sentMessage");
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            Log.d("receive", "receiveMessage");
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("onbind", "onBindViewHolder: ");
        //for(int i = 0; i <= position; i++){
        String message = String.valueOf(mMessageList.get(position));

        switch (getItemViewType(position)) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
        //}
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);
            Log.d("working", "SentMessageHolder: ");

            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);


        }

        void bind(String message) {
            Log.d("working", "bind: ");
            messageText.setTextSize(MainActivity.textsizenumbermain + 12);

            //message = TextInput;
            Log.d("message", String.valueOf(message.substring(2)));
            Log.d("showmessagetime", String.valueOf(MainActivity.showmessagetimemain));
            if (MainActivity.showmessagetimemain) {
                timeText.setVisibility(View.INVISIBLE);
            } else {
                timeText.setVisibility(View.VISIBLE);
            }

            messageText.setText(message.substring(2));

            // Format the stored timestamp into a readable String using method.
            timeText.setText(String.valueOf(TimeDate.getCreatedAt()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            Log.d("working", "receivedmessagehodler: ");


            messageText = itemView.findViewById(R.id.text_message_received);
            timeText = itemView.findViewById(R.id.text_message_received_time);
            nameText = itemView.findViewById(R.id.text_message_received_name);
            profileImage = itemView.findViewById(R.id.text_message_received_profile_pic);


        }

        void bind(String message) {
            Log.d("working", "bind: ");
            messageText.setTextSize(MainActivity.textsizenumbermain + 12);

            Log.d("showmessagetime", String.valueOf(MainActivity.showmessagetimemain));
            if (MainActivity.showmessagetimemain) {
                timeText.setVisibility(View.INVISIBLE);
            } else {
                timeText.setVisibility(View.VISIBLE);
            }

            Log.d("showboticon", String.valueOf(MainActivity.iconbothidemain));
            if (MainActivity.iconbothidemain) {
                profileImage.setVisibility(View.INVISIBLE);
            } else {
                profileImage.setVisibility(View.VISIBLE);

            }
            //message = TextOutput;
            Log.d("message", String.valueOf(message.substring(2)));
            messageText.setText(message.substring(2));

            // Format the stored timestamp into a readable String using method.
            timeText.setText(String.valueOf(TimeDate.getCreatedAt()));

            nameText.setText("TheraBot");

            // Insert the profile image from the URL into the ImageView.
            //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }
}
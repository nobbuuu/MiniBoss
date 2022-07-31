package com.dream.miniboss.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.miniboss.message.bean.MessageChatDetailBean;

import java.util.List;

public class MessageChatDetailAdapter extends RecyclerView.Adapter<MessageChatDetailAdapter.ViewHolder> {
    private static final String SEND_MESSAGE_TYPE="SEND_MESSAGE_TYPE";
    private static final String RECEIVE_MESSAGE_TYPE="RECEIVE_MESSAGE_TYPE";

    List<MessageChatDetailBean> messageList;
    Context mContext;

    public MessageChatDetailAdapter(List<MessageChatDetailBean> messageList, Context mContext) {
        this.messageList = messageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messageList==null?null:messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

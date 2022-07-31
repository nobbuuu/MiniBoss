package com.dream.miniboss.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.miniboss.R;
import com.dream.miniboss.message.bean.MessageChatDetailBean;

import java.util.List;

public class MessageChatDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<MessageChatDetailBean> messageList;
    Context mContext;

    public MessageChatDetailAdapter(List<MessageChatDetailBean> messageList, Context mContext) {
        this.messageList = messageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 根据类型来决定加上什么holder
        if (viewType == MessageChatDetailBean.TYPE_RECEIVED) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_left_item, parent, false);
            return new leftHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_right_item, parent, false);
            return new rightHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageChatDetailBean message = messageList.get(position);
        // 根据不同的holder来决定加载数据
        if (holder instanceof rightHolder) {
            ((rightHolder) holder).textView.setText(message.getMessage());
        }
        if (holder instanceof leftHolder) {
            ((leftHolder) holder).textView.setText(message.getMessage());
        }
    }


    @Override
    public int getItemCount() {
        return messageList == null ? null : messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageChatDetailBean massageBean = messageList.get(position);
        return massageBean.getType();
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
    public  class rightHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public rightHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.chat_right_text_view);
        }
    }

    public  class leftHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public leftHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.chat_left_text_view);
        }
    }

}

package com.dream.miniboss.message.adapter;

import static com.airbnb.lottie.L.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.miniboss.R;
import com.dream.miniboss.message.bean.MessageChatBean;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import java.util.List;

/**
 * 创建日期：2022-07-17 on 1:36
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class MessageChatAdapter extends RecyclerView.Adapter<MessageChatAdapter.ViewHolder> {
    List<MessageChatBean> mList;
    Context mContext;
    ItemClickListener mItemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public MessageChatAdapter(List<MessageChatBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public MessageChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(mContext).inflate(R.layout.item_message_chat,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageChatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if (mItemClickListener!=null){
                       mItemClickListener.OnClick(position);
                   }
               }
           });
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?null:mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RImageView userIcon;
        RTextView userName;
        RTextView company;
        RTextView content;
        RTextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon=itemView.findViewById(R.id.user_message_icon);
            userName=itemView.findViewById(R.id.user_name_message);
            company=itemView.findViewById(R.id.message_company_tv);
            content=itemView.findViewById(R.id.message_content_tv);
            date=itemView.findViewById(R.id.message_date_tv);
        }
    }

    public interface ItemClickListener{
         void OnClick(int position);
         void LongOnClick(int position);
    }
}

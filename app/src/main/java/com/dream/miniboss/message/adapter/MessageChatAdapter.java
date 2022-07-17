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
import androidx.recyclerview.widget.ItemTouchHelper;
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

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_message_chat, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageChatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mItemClickListener != null) {
                    mItemClickListener.OnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? null : mList.size();
    }

    //删除item条目
    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RImageView userIcon;
        RTextView userName;
        RTextView company;
        RTextView content;
        RTextView date;
        RTextView deleteTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_message_icon);
            userName = itemView.findViewById(R.id.user_name_message);
            company = itemView.findViewById(R.id.message_company_tv);
            content = itemView.findViewById(R.id.message_content_tv);
            date = itemView.findViewById(R.id.message_date_tv);
            deleteTv=itemView.findViewById(R.id.delete_item_tv);
        }
    }

    public interface ItemClickListener {
        void OnClick(int position);

        void LongOnClick(int position);
    }


    public static class MovieItemTouchHelper extends ItemTouchHelper.SimpleCallback {

        private MessageChatAdapter adapter;

        public MovieItemTouchHelper(MessageChatAdapter adapter) {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.adapter = adapter;
        }

        /**
         * If you don't support drag & drop, this method will never be called.
         * 如果不支持拖拽，那么这个方法就不会被执行。
         *
         * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to. ItemTouchHelper需要附加到的RecyclerView
         * @param viewHolder   The ViewHolder which is being dragged by the user. 拖动的ViewHolder
         * @param target       The ViewHolder over which the currently active item is being dragged. 目标位置的ViewHolder
         * @return True if the viewHolder has been moved to the adapter position of target. viewHolder是否被移动到目标位置
         */

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        /**
         * Called when a ViewHolder is swiped by the user.
         * If you don't support swiping, this method will never be called.
         * 如果不支持滑动，方法不会被执行。
         *
         * @param viewHolder The ViewHolder which has been swiped by the user.
         * @param direction  The direction to which the ViewHolder is swiped.
         *                   It is one of UP, DOWN, LEFT or RIGHT.
         *                   If your getMovementFlags(RecyclerView, ViewHolder) method returned relative flags instead of LEFT / RIGHT;
         *                   `direction` will be relative as well. (START or END).
         */

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //Remove item
            viewHolder.itemView.findViewById(R.id.delete_item_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                adapter.remove(viewHolder.getAdapterPosition());
                MovieItemTouchHelper.this.notify();
                }
            });

        }

    }
}

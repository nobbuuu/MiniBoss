package com.dream.miniboss.message.adapter;

import static com.airbnb.lottie.L.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.MiniBossAppKt;
import com.dream.miniboss.R;
import com.dream.miniboss.message.bean.MessageChatBean;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;
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
//        holder.deleteTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                remove(position);
//                notifyItemRemoved(position);
//            }
//        });
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
            deleteTv = itemView.findViewById(R.id.delete_item_tv);
        }
    }

    public interface ItemClickListener {
        void OnClick(int position);

        void LongOnClick(int position);
    }


    public static class CallBack extends ItemTouchHelper.Callback {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            /**
             * call max distance start onSwiped call
             */
        }


        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            RTextView mTextView;
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                /**
                 * get {@link TextView#getWidth()}
                 */
                ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;

                mTextView = (RTextView) viewGroup.getChildAt(1);

                ViewGroup.LayoutParams layoutParams = mTextView.getLayoutParams();
//                ToastUtils.showShort(layoutParams.width);
                if (Math.abs(dX) <= layoutParams.width) {
                    ToastUtils.showShort("-------" + mTextView.getText().toString());
                    /**
                     * move {@link RecyclerView.ViewHolder} distance
                     */
                    viewHolder.itemView.scrollTo((int) -dX % layoutParams.width, 0);
                    /**
                     * callAction or register click bind view
                     */

                }


            }

        }

        @Override
        public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
        }
    }
}

package com.dream.miniboss.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.miniboss.R;
import com.dream.miniboss.bean.BlackListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 创建日期：2022-06-28 on 13:07
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class BlackListAdapter extends RecyclerView.Adapter<BlackListAdapter.ViewHolder> {
    List<BlackListBean>mList;

    Context mContext;


    public BlackListAdapter(List<BlackListBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public BlackListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View convertView=mInflater.inflate(R.layout.item_blacklist_activity,null);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlackListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
               holder.removeImage.setOnClickListener(new View.OnClickListener() {


                   @Override
                   public void onClick(View v) {
                       Log.d("TAG", "onClick: "+"这是删除条目点击"+position);
                       mList.remove(position);
                      notifyItemRemoved(position);
                       notifyDataSetChanged();
                   }
               });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ImageView iconImage;
         TextView name;
         TextView introduce;
         ImageView removeImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           iconImage=itemView.findViewById(R.id.iv_black_list_icon);
           name=itemView.findViewById(R.id.tv_black_list_name);
           introduce=itemView.findViewById(R.id.tv_black_list_introduce);
           removeImage=itemView.findViewById(R.id.iv_black_list_remove);
        }
    }

    private class TAG {
    }
}

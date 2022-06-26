package com.dream.miniboss.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.miniboss.R;
import com.dream.miniboss.bean.PugongBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 创建日期：2022-06-21 on 14:06
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PugongXRvAdpter extends XRecyclerView.Adapter<PugongXRvAdpter.ViewHolder> {
    public List<PugongBean> mList;
    public Context mContext;
    public LayoutInflater mInflater;

    public PugongXRvAdpter(List<PugongBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
        mInflater=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=mInflater.inflate(R.layout.item_xrv,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//       holder.gongzuogangwei.setText(mList.get(position).getName());
//       holder.gongzi.setText(mList.get(position).getGongzi()+"元/月");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView gongzuogangwei;
        TextView gongzi;
        TextView xueli;
        TextView nianlin;
        TextView jingyan;
        TextView renshu;
        ImageView icon;
        TextView xingming;
        TextView gerenzhaopin;
        TextView renzheng;
        TextView zaixian;
        TextView company;
        TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             gongzuogangwei=itemView.findViewById(R.id.tv_gongzuogangwei);
            gongzi=itemView.findViewById(R.id.tv_gongzi);
            xueli=itemView.findViewById(R.id.tv_xueli);
            nianlin=itemView.findViewById(R.id.tv_nianlin);
            jingyan=itemView.findViewById(R.id.tv_jingyan);
            renshu=itemView.findViewById(R.id.tv_renshu);
            icon=itemView.findViewById(R.id.image_icon);
            xingming=itemView.findViewById(R.id.tv_name);
            gerenzhaopin=itemView.findViewById(R.id.tv_qiyezhaopin);
            renzheng=itemView.findViewById(R.id.tv_qiyerenzheng);
            zaixian=itemView.findViewById(R.id.tv_zaixian);
            company=itemView.findViewById(R.id.tv_compny);
            address=itemView.findViewById(R.id.tv_address);


        }
    }
}

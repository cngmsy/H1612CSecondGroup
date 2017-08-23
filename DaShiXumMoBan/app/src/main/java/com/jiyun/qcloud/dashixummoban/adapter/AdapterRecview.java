package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;

import java.util.List;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by KING on 2017/8/12 11:48
 * 邮箱:992767879@qq.com
 */

/**
 * Created by KING on 2017/8/21 22:09
 * 邮箱:992767879@qq.com
 */

public class AdapterRecview extends RecyclerView.Adapter {
        private List<Shouye2.BodyBean> list;
        private Context context;
        private Tiaozhuan tiaozhuan;
        public AdapterRecview(List<Shouye2.BodyBean> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder=null;
            switch (viewType){
                case 0:
                    View inflate = LinearLayout.inflate(context, R.layout.item_listview, null);
                    viewHolder=new Neibulei(inflate);
                    break;
                case 1:
                    View inflate1 = LayoutInflater.from(context).inflate( R.layout.item_buju1, parent,false);
                    viewHolder=new Neibulei1(inflate1);
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            int itemViewType = getItemViewType(position);
            switch (itemViewType){
                case 0:
                    Neibulei holder1 = (Neibulei) holder;
                    holder1.name.setText(list.get(position).getSeller().getName());
                    holder1.itemView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tiaozhuan.tiao(view,position);
                        }
                    });
                    break;
                case 1:
                    Neibulei1 holder2 = (Neibulei1) holder;
                    Shouye2.BodyBean bodyBean = list.get(position);
                    List<String> recommendInfos = (List<String>) bodyBean.getRecommendInfos();
                    holder2.zhao1.setText(recommendInfos.get(0));
                    holder2.zhao2.setText(recommendInfos.get(1));
                    holder2.zhao3.setText(recommendInfos.get(2));
                    holder2.zhao4.setText(recommendInfos.get(0));
                    holder2.zhao5.setText(recommendInfos.get(1));
                    holder2.zhao6.setText(recommendInfos.get(2));
                    break;
            }

        }
        @Override
        public int getItemCount() {
            return list.size();
        }

        private class Neibulei extends RecyclerView.ViewHolder {

            private final TextView name;
            private final View itemView1;

            public Neibulei(View itemView) {
                super(itemView);
                itemView1 = itemView;
                name =  itemView.findViewById(R.id.name1);
            }
        }
        private class Neibulei1 extends RecyclerView.ViewHolder {

            private final TextView zhao1;
            private final TextView zhao2;
            private final TextView zhao3;
            private final TextView zhao4;
            private final TextView zhao5;
            private final TextView zhao6;

            public Neibulei1(View itemView) {
                super(itemView);
                zhao1 = itemView.findViewById(R.id.zhao1);
                zhao2 = itemView.findViewById(R.id.zhao2);
                zhao3 =  itemView.findViewById(R.id.zhao3);
                zhao4 = itemView.findViewById(R.id.zhao4);
                zhao5 =  itemView.findViewById(R.id.zhao5);
                zhao6 =  itemView.findViewById(R.id.zhao6);
            }
        }
        public interface Tiaozhuan{
            void tiao(View view,int i);
        }
        public void onLintern(Tiaozhuan tiaozhuan){
            this.tiaozhuan=tiaozhuan;
        }

    }



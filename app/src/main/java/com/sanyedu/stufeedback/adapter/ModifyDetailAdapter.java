package com.sanyedu.stufeedback.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.DetailedList;

import java.util.List;

public class ModifyDetailAdapter extends RecyclerView.Adapter<ModifyDetailAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<DetailedList> list;
    private View view ;

    public ModifyDetailAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = inflater.inflate(R.layout.item_modified_detail,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DetailedList detail = list.get(i);

        if (detail != null){
//            myViewHolder.headIv.setBackground();
            myViewHolder.nameTv.setText(detail.getFeedbackPername());
            myViewHolder.longTimeTv.setText(detail.getFeedbackTime());
            myViewHolder.contentTv.setText(detail.getFeedbackContent());
        }
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView headIv;
        private TextView nameTv;
        private TextView longTimeTv;
        private TextView contentTv;
        private View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.headIv = itemView.findViewById(R.id.head_iv);
            this.nameTv = itemView.findViewById(R.id.name_tv);
            this.longTimeTv = itemView.findViewById(R.id.long_ago_tv);
            this.contentTv = itemView.findViewById(R.id.content_tv);
        }


    }

    public void setList(List<DetailedList> list){
        this.list = list;
        notifyDataSetChanged();
    }

}

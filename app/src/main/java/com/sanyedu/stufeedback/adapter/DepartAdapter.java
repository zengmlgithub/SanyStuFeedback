package com.sanyedu.stufeedback.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.DepartModel;

import java.util.ArrayList;
import java.util.List;


public class DepartAdapter extends BaseAdapter {

    private List<DepartModel> departList = new ArrayList<>();
    private LayoutInflater inflater;

    public DepartAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return departList.size();
    }

    @Override
    public DepartModel  getItem(int position) {
        return departList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView=inflater.inflate(R.layout.item_spinner, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.person_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DepartModel departBean = departList.get(position);
        if(departBean != null){
            viewHolder.name.setText(departBean.getFullname());
        }else{
            //TODO:如果没有数据的时候
        }
        return convertView;
    }

    class ViewHolder{
        TextView name;
    }

    public void setData(List<DepartModel> departList){
        this.departList = departList;
        notifyDataSetChanged();
    }
}

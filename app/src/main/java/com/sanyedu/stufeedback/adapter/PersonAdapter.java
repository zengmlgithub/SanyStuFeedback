package com.sanyedu.stufeedback.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter {

    private List<PersonModel> personList = new ArrayList<>();
    private LayoutInflater inflater;

    public PersonAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public PersonModel getItem(int position) {
        return personList.get(position);
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

        PersonModel departBean = personList.get(position);
        if(departBean != null){
            viewHolder.name.setText(departBean.getTeName());
        }else{
            //TODO:如果没有数据的时候
        }
        return convertView;
    }

    class ViewHolder{
        TextView name;
    }

    public void setData(List<PersonModel> departList){
        this.personList = departList;
        notifyDataSetChanged();
    }
}

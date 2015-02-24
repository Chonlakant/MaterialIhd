package com.tekinarslan.material.sample;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterJson extends BaseAdapter {


    Context context;


    ArrayList<ItemAdapter> list = new ArrayList<ItemAdapter>();

    public AdapterJson(Context context,ArrayList<ItemAdapter> list){
        this.context = context;

        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.page,parent,false);


        ItemAdapter item = list.get(position);

        TextView month = (TextView) row.findViewById(R.id.Aung);
        TextView date = (TextView) row.findViewById(R.id.day);
        TextView number1 = (TextView) row.findViewById(R.id.number1);
        TextView number2 = (TextView) row.findViewById(R.id.number2);
        TextView number3 = (TextView) row.findViewById(R.id.number3);
        TextView number4 = (TextView) row.findViewById(R.id.GetNotifications);
        TextView messen = (TextView) row.findViewById(R.id.messng);
        TextView view = (TextView) row.findViewById(R.id.view);
        ImageView ImageUrl = (ImageView) row.findViewById(R.id.imageView);
        ImageView image_messen = (ImageView) row.findViewById(R.id.image_center);


        month.setText(item.getMonth());
        date.setText(item.getDate());
        number1.setText(item.getNumber1());
        number2.setText(item.getNumber2());
        number3.setText(item.getNumber3());
        number4.setText(item.getNumber4());
        messen.setText(item.getMessen());
        view.setText(item.getView());



        Picasso.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_ab_drawer)
                .fit()
                .into(ImageUrl);

        Picasso.with(context)
                .load(item.getImage_messen())
                .placeholder(R.drawable.ic_ab_drawer)
                .fit()
                .into(image_messen);

        return row;
    }
}

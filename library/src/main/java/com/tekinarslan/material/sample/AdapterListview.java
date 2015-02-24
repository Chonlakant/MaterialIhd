package com.tekinarslan.material.sample;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AdapterListview extends BaseAdapter {

    Context context;
    String[] name;
    String[] name2;
    String[] name3;
    String[] name4;
    String[] name5;
    String[] name6;
    String[] name7;




//    int[] id;
//    int[] id2;

    public AdapterListview(Context context, String[] name, int[] id, String[] name2, String[] name3, String[] name4,
                           String[] name5, String[] name6, String[] name7) {
        this.context = context;
        this.name = name;
       // this.id = id;
        //this.id2 = id2;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name5 = name5;
        this.name6 = name6;
        this.name7 = name7;





    }



    @Override
    public int getCount() {
        return name.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mlayout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = mlayout.inflate(R.layout.page, parent, false);

        TextView title = (TextView) row.findViewById(R.id.Aung); // title
        TextView day = (TextView) row.findViewById(R.id.day);
        TextView number1 = (TextView) row.findViewById(R.id.number1); // artist name
        TextView number2 = (TextView) row.findViewById(R.id.number2); // duration
        TextView number3 = (TextView) row.findViewById(R.id.number3);
        TextView GetNotifications = (TextView) row.findViewById(R.id.GetNotifications);
        TextView messng = (TextView) row.findViewById(R.id.messng);
        ImageView thumb_image = (ImageView) row.findViewById(R.id.imageView);

        title.setText(name[position]);
        number1.setText(name2[position]);
        number2.setText(name3[position]);
        number3.setText(name4[position]);
        GetNotifications.setText(name5[position]);
        day.setText(name6[position]);
        messng.setText(name7[position]);

        //thumb_image.setBackgroundResource(id[position]);
        Picasso.with(context).load("http://icons.iconarchive.com/icons/hopstarter/sleek-xp-software/256/Yahoo-Messenger-icon.png").into(thumb_image);


        return row;
    }
}


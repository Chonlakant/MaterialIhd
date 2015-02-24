package net.com.io.ihd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.com.io.ihd.R;
import net.com.io.ihd.model.Posts;
import net.com.io.ihd.views.GridItemImageView;

import java.util.ArrayList;

/**
 * Created by root1 on 2/24/15.
 */
public class AdapterMovie extends BaseAdapter {
    public Context context;
    public ArrayList<Posts> list = new ArrayList<Posts>();

    public  AdapterMovie(Context context,ArrayList<Posts> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = mInflater.inflate(R.layout.item_movie, parent, false);

        Posts movie = list.get(position);
        TextView nameMovie = (TextView) row.findViewById(R.id.movie_title);
        TextView cerateTime = (TextView) row.findViewById(R.id.movie_date);
        nameMovie.setText(movie.getNameMovie());
        cerateTime.setText(movie.getCreateTime());

        GridItemImageView imageUrlMovie = (GridItemImageView) row.findViewById(R.id.movie_poster);

        Picasso.with(context)
                .load(movie.getImageMovie())
                .fit()
                .into(imageUrlMovie);
        return row;
    }
}

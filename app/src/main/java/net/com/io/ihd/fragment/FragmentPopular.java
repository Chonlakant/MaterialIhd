package net.com.io.ihd.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import net.com.io.ihd.R;
import net.com.io.ihd.activities.VideoStreamPlayer;
import net.com.io.ihd.adapters.AdapterMovie;
import net.com.io.ihd.model.Posts;
import net.com.io.ihd.widget.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentPopular extends Fragment {
    String url = "http://ihdmovie.xyz/root/api_movie/get_movie2.php?uid=1&cat=1";
    AdapterMovie adapterMovie;
    GridView gridView;
    Context context;
    Dialog dialog;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    ArrayList<Posts> arrayList = new ArrayList<Posts>();
    ProgressBar progressBar;

    public AQuery aq;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        context = getActivity();
        aq = new AQuery(getActivity());
        adapterMovie = new AdapterMovie(getActivity(),arrayList);
        gridView = (GridView) rootView.findViewById(R.id.movies_list);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        gridView.setAdapter(adapterMovie);
        progressBar.setVisibility(View.GONE);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.detail_dialog);
                dialog.setTitle(arrayList.get(position).getNameMovie());


                ButtonRectangle btn1 = (ButtonRectangle) dialog.findViewById(R.id.button1);
                btn1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String videoUrl = arrayList.get(position).getUrlVideolMovie();
                        String imageUrl = arrayList.get(position).getImageMovie();
                        String title = arrayList.get(position).getNameMovie();

                        Intent i2 = new Intent(getActivity(),VideoStreamPlayer.class);


                        i2.putExtra("VdoUrl", videoUrl);
                        i2.putExtra("ImageUrl", imageUrl);
                        i2.putExtra("title", title);
                        startActivity(i2);
                    }
                });

                ButtonRectangle btn3 = (ButtonRectangle) dialog.findViewById(R.id.btnShare);
                btn3.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String videoUrl = arrayList.get(position).getUrlVideolMovie();

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                        i.putExtra(Intent.EXTRA_TEXT, videoUrl);

                        // Toast.makeText(getApplicationContext(), ""+videoUrl,
                        // Toast.LENGTH_SHORT).show();

                        startActivity(Intent.createChooser(i, "Share URL"));

                    }
                });

                ButtonRectangle btn2 = (ButtonRectangle) dialog.findViewById(R.id.button2);
                btn2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                TextView titlemovie = (TextView) dialog
                        .findViewById(R.id.text_title);
                titlemovie.setText(arrayList.get(position).getNameMovie());

                NetworkImageView image = (NetworkImageView) dialog
                        .findViewById(R.id.image);
                image.setImageUrl(arrayList.get(position).getImageMovie(), imageLoader);

                // Google Admob
                Location location = new Location("AdMobProvider");
                location.setLatitude(13.543296);
                location.setLatitude(100.924562);

                AdRequest.Builder adBuilder = new AdRequest.Builder();
                adBuilder.setLocation(location);

                AdRequest adRequest = adBuilder.build();
                AdView adView = (AdView) dialog.findViewById(R.id.adView);
                adView.loadAd(adRequest);


                dialog.show();
            }
        });

        aq.ajax(url, JSONObject.class, this, "getjson");

        return rootView;
    }
    public void getjson(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        AQUtility.debug("jo", jo);

        if (jo != null) {
            JSONArray ja = jo.optJSONArray("posts");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject obj = ja.optJSONObject(i);
                Log.d("Check:",obj.toString());

                String imageMovie = obj.getString("image");
                String movieName = obj.getString("name");
                String createTime = obj.getString("id");

//                String timeShot = createTime;
//                String[] shorts = timeShot.split("-");
//
//                String string = movieName;
//                String[] parts = string.split("http");
//                String part2 = parts[1];


                //String[] parts2 = string.split("(");
                // String part21 = parts2[0];

                //String movieUrl = "http" + part2;
                String movieUrl = obj.getString("url");

//                Log.d("Check",shorts[0]);

                Posts posts = new Posts(movieName,imageMovie,movieUrl,createTime);
                arrayList.add(posts);

            }
            adapterMovie.notifyDataSetChanged();
            AQUtility.debug("done");

        } else {
            AQUtility.debug("error!");
        }
    }
}
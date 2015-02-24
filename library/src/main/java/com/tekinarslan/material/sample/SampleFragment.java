package com.tekinarslan.material.sample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.getbase.floatingactionbutton.FloatingActionButton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SampleFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    public AQuery aq;
    private int position;
    String url = "http://ihdmovie.xyz/root/api/feed_get.php?uid=1";
    RelativeLayout layoutMenu;
    ArrayList<ItemAdapter> list = new ArrayList<ItemAdapter>();
    AdapterJson adapterJson;



    ListView listView;



    public static SampleFragment newInstance(int position) {
        SampleFragment f = new SampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.listview_main, container, false);

        aq = new AQuery(getActivity());

        listView = (ListView) rootView.findViewById(R.id.listView);
        layoutMenu = (RelativeLayout)rootView.findViewById(R.id.layoutMenu);

        adapterJson = new AdapterJson(getActivity(),list);
        listView.setAdapter(adapterJson);
        listView.setOnTouchListener(new View.OnTouchListener() {

            final int DISTANCE = 3;

            float startY = 0;
            float dist = 0;
            boolean isMenuHide = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    startY = event.getY();
                } else if (action == MotionEvent.ACTION_MOVE) {
                    dist = event.getY() - startY;

                    if ((pxToDp((int) dist) <= -DISTANCE) && !isMenuHide) {
                        isMenuHide = true;
                        hideMenuBar();
                    } else if ((pxToDp((int) dist) > DISTANCE) && isMenuHide) {
                        isMenuHide = false;
                        showMenuBar();
                    }

                    if ((isMenuHide && (pxToDp((int) dist) <= -DISTANCE))
                            || (!isMenuHide && (pxToDp((int) dist) > 0))) {
                        startY = event.getY();
                    }
                } else if (action == MotionEvent.ACTION_UP) {
                    startY = 0;
                }

                return false;
            }
        });
        FloatingActionButton btn_post = (com.getbase.floatingactionbutton.FloatingActionButton) rootView.findViewById(R.id.action_a);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Click_Test",Toast.LENGTH_LONG).show();
            }
        });

        aq.ajax(url, JSONObject.class, this, "getjson");

        return rootView;
    }

    public void getjson(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        AQUtility.debug("jo", jo);

        if (jo != null) {
            JSONArray ja = jo.getJSONArray("posts");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject obj = ja.getJSONObject(i);

                //Log.d("Check",obj.toString());
                String ImageUrl = obj.getString("image");
                String month = obj.getString("month");
                String date = obj.getString("date");
                String number1 = obj.getString("number1");
                String number2 = obj.getString("number2");
                String number3 = obj.getString("number3");
                String number4 = obj.getString("number4");
                String messen = obj.getString("messen");
                String view = obj.getString("view");
                String image_messen = obj.getString("image_messen");


                ItemAdapter list_item = new ItemAdapter();
                list_item.setImageUrl(ImageUrl);
                list_item.setMonth(month);
                list_item.setDate(date);
                list_item.setNumber1(number1);
                list_item.setNumber2(number2);
                list_item.setNumber3(number3);
                list_item.setNumber4(number4);
                list_item.setMessen(messen);
                list_item.setImage_messen(image_messen);
                list_item.setView(view);
                Log.d("Check",ImageUrl);

                list.add(list_item);
            }
            adapterJson.notifyDataSetChanged();
            AQUtility.debug("done");

        } else {
            AQUtility.debug("error!");
        }
    }
    public int pxToDp(int px) {
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        int dp = Math.round(px / (dm.densityDpi
                / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public void showMenuBar() {
        AnimatorSet animSet = new AnimatorSet();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(layoutMenu
                , View.TRANSLATION_Y, 0);

        animSet.playTogether(anim1);
        animSet.setDuration(300);
        animSet.start();
    }

    public void hideMenuBar() {
        AnimatorSet animSet = new AnimatorSet();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(layoutMenu
                , View.TRANSLATION_Y, layoutMenu.getHeight());

        animSet.playTogether(anim1);
        animSet.setDuration(300);
        animSet.start();
    }

}
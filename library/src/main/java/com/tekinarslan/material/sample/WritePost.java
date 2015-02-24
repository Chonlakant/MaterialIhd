package com.tekinarslan.material.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WritePost   extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.write_post, container, false);
        getFragmentManager().beginTransaction().remove(WritePost.this).commit();
        return rootView;
    }
}

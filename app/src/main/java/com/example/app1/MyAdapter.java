package com.example.app1;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends ArrayAdapter {

    private static final String TAG = "MyAdapter";

    public MyAdapter(Context context, int resource, ArrayList<HashMap<String,String>> list) {
        super(context, resource, list);
    }

}

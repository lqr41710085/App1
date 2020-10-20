package com.example.app1;

import android.util.Log;

class RateItem {
    private String name;
    private String rate;
    private int ID;
    private static String TAG="RateItem";
    public String getCurName(){
        return name;
    }
    public String getCurRate(){
        return rate;
    }
    public void setCurName(String n){
        name=n;
    }
    public void setCurRate(String r){
        rate=r;
        Log.i(TAG,"hhhset:ok  id:"+ID);
    }
    public void setId(int id){
        ID=id;
    }
    public int getId(){
        return ID;
    }

}

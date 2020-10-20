package com.example.app1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

public class RateListActivity extends ListActivity implements Runnable{
    private  static final String TAG="RateListActivity";
    Handler handler;
    ArrayList<HashMap<String, String>> listItems;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    RateManager ratemanager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rate_list);

        Thread t=new Thread(this);
        t.start();

        sp=this.getSharedPreferences("rate",MODE_PRIVATE);

        handler=new Handler(){
            public void handleMessage(Message msg){

                if(msg.what==4){
                    Bundle bd=(Bundle)msg.obj;
                    String time=bd.get("time").toString().substring(12,22);
                    String lasttime=sp.getString("time","0000-00-00");
                    Log.i(TAG,"hhhh"+time+"--"+lasttime);

                    ratemanager=new RateManager(RateListActivity.this);

                   if(!time.equals(lasttime)){//时间不同则更新,同时记录时间
                       Log.i(TAG,"hhh更新");
                        listItems= new ArrayList<HashMap<String, String>>();
                        editor=sp.edit();
                        editor.putString("time",time);
                        editor.apply();
                        int i=0;//从1开始id
                        for(String key:bd.keySet()){
                            if(!key.equals("time")) {
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("name", key);
                                map.put("rate", String.valueOf(bd.getFloat(key)));
                                listItems.add(map);
                                //数据库更新
                                RateItem item = ratemanager.findById(++i);
                                item.setCurName(key);
                                item.setCurRate(String.valueOf(bd.getFloat(key)));
                                ratemanager.update(item);
                            }
                        }
                    }
                  else
                       {
                        //时间相同，从数据库中读取
                        Log.i(TAG, "hhh不更新" );
                        listItems= new ArrayList<HashMap<String, String>>();
                        int i=0;
                        for(String key:bd.keySet()) {
                            if(!key.equals("time")) {
                                RateItem item = ratemanager.findById(++i);
                                String rate = item.getCurRate();
                                String name = item.getCurName();
                                Log.i(TAG, "hhh" + name + "--" + rate);
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("name", name);
                                map.put("rate", rate);
                                listItems.add(map);
                            }
                         }
                    }

                   MyAdapter myAdapter=new MyAdapter(RateListActivity.this, R.layout.activity_rate_list,listItems);
                   setListAdapter(myAdapter);

                }
                super.handleMessage(msg);

            }
        };


   }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {


            Object itemAtPosition = getListView().getItemAtPosition(position);
            HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;

            String titleStr = map.get("name");
            String detailStr = map.get("rate");


            super.onListItemClick(l, v, position, id);
            Intent intent =  new Intent(this, CalcActivity.class);
            intent.putExtra("name",titleStr);
            intent.putExtra("rate",detailStr);
           // Log.i(TAG,"hhhh"+titleStr+":"+detailStr);
            this.startActivity(intent);

    }

    @Override
    public void run() {
        Document doc= null;
        Bundle bd=new Bundle();

        try {
            String url="https://www.usd-cny.com/bankofchina.htm";
            doc = Jsoup.connect(url).get();
           // Log.i(TAG,"hhhconnect"+doc.title());
            Elements tables= doc.getElementsByTag("table");
            Element table=tables.first();
            Elements tds=table.getElementsByTag("td");
            Elements ps=doc.getElementsByTag("p");
            Element p=ps.get(0);
            bd.putString("time",p.text());
            for(int i=0;i<tds.size();i+=6){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String name=td1.text();
                String rate=td2.text();
                float v=100f/Float.parseFloat(rate);
                bd.putFloat(name,v);
              //  Log.i(TAG,name+"hhhhh: "+v+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(4);
        msg.obj=bd;
        handler.sendMessage(msg);
       // Log.i(TAG,"hhhhsend");
    }

}


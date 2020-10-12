package com.example.app1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateListActivity extends ListActivity implements Runnable{
    private  static final String TAG="RateListActivity";
    Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rate_list);

        Thread t=new Thread(this);
        t.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==4){
                    List<String> list=new ArrayList<String>();
                    Bundle bd=(Bundle)msg.obj;
                    for(String key:bd.keySet()){
                        list.add(key+"==>"+bd.getFloat(key));
                    }

                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this,android.R.layout.simple_list_item_1,list);

                   /*for(int i=0;i<4;i++){
                        HashMap<String,String> map=new HashMap<String, String>();
                        map.put(name[i],name[i]);
                        map.put(name[i]+"汇率",String.valueOf(bd.getFloat(name[i])));
                        listItems.add(map);
                    }
                    */
                   setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };


    }

    @Override
    public void run() {
        Document doc= null;
        Bundle bd=new Bundle();
        Log.i(TAG,"hhhconnect");
        try {
            String url="https://www.usd-cny.com/bankofchina.htm";
            doc = Jsoup.connect(url).get();
            Log.i(TAG,"hhhhhdoc:"+doc.title());
            Elements tables= doc.getElementsByTag("table");
            Element table=tables.first();
            Elements tds=table.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String name=td1.text();
                String rate=td2.text();
                float v=100f/Float.parseFloat(rate);
                bd.putFloat(name,v);
                Log.i(TAG,name+"hhhhh: "+v+"\n");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(4);
        msg.obj=bd;
        handler.sendMessage(msg);
        Log.i(TAG,"hhhhsend");
    }
}

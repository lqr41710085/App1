package com.example.app1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rate_list);

        Thread t=new Thread(this);
        t.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==4){
                    Bundle bd=(Bundle)msg.obj;
                   /* List<String> list=new ArrayList<String>();

                    for(String key:bd.keySet()){
                        list.add(key+"==>"+bd.getFloat(key));
                    }

                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this,android.R.layout.simple_list_item_1,list);
                    */
                    listItems= new ArrayList<HashMap<String, String>>();
                   for(String key:bd.keySet()){
                        HashMap<String,String> map=new HashMap<String, String>();
                        map.put("name",key);
                        map.put("rate",String.valueOf(bd.getFloat(key)));
                        Log.i(TAG,"hhh"+map.toString());
                        listItems.add(map);

                    }
                   // SimpleAdapter adapter = new SimpleAdapter(RateListActivity.this, listItems, R.layout.activity_rate_list, new String[]{"name", "rate"}, new int[]{R.id.itemTitle, R.id.itemDetail});
                   // setListAdapter(adapter);
                    MyAdapter myAdapter=new MyAdapter(RateListActivity.this, R.layout.activity_rate_list,listItems);
                    setListAdapter(myAdapter);

                }
                super.handleMessage(msg);

            }
        };
        this.getListView().setEmptyView(findViewById(R.id.nodata));

   }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

            Object itemAtPosition = getListView().getItemAtPosition(position);
            HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;

            String titleStr = map.get("name");
            String detailStr = map.get("rate");
           /*
           TextView title = (TextView) v.findViewById(R.id.itemTitle);
            TextView detail = (TextView)v.findViewById(R.id.itemDetail);


            String title2 = String.valueOf(title.getText());
            String detail2 = String.valueOf(detail.getText());
            */
            super.onListItemClick(l, v, position, id);
            Intent intent =  new Intent(this, CalcActivity.class);
            intent.putExtra("name",titleStr);
            intent.putExtra("rate",detailStr);
            Log.i(TAG,"hhhh"+titleStr+":"+detailStr);
            this.startActivity(intent);

    }

    @Override
    public void run() {
        Document doc= null;
        Bundle bd=new Bundle();

        try {
            String url="https://www.usd-cny.com/bankofchina.htm";
            doc = Jsoup.connect(url).get();
            Log.i(TAG,"hhhconnect"+doc.title());
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


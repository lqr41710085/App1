package com.example.app1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements Runnable{
    double dol,eur,jap,hk;
    TextView edit,text;
    private  static final String TAG="MainActivity";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Handler handler;
    @Override
    public void run() {
        String html="";
        URL url =null;
        Log.i(TAG,"hhhrun:ok");
        try {
            url = new URL("https://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            Log.i(TAG,"hhhconnect to internet");
            InputStream in = http.getInputStream();
            html=inputStream2String(in);
            Log.i(TAG,"hhhhtml:ok");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(4);
        msg.obj=html;
        handler.sendMessage(msg);

        Log.i(TAG,"hhhsend:"+html);
    }

    private String inputStream2String(InputStream inputStream) throws IOException{
        String str="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"gb2312"));
        StringBuffer sb=new StringBuffer();
        while((str=reader.readLine())!=null){
            Log.i(TAG,"in2str:"+str);
            sb.append(str).append("\n");
        }
        return  sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t=new Thread(this);
        t.start();
        Log.i(TAG,"hhhstart:ok");
        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==4){
                    String str=(String)msg.obj;
                    Log.i(TAG,"hhhhandler:"+str);

                }
                super.handleMessage(msg);
            }
        };
        sp=this.getSharedPreferences("rate",MODE_PRIVATE);
        editor=sp.edit();
        /*.putFloat("dol",0.147f);
        editor.putFloat("eur",0.125f);
        editor.putFloat("jap",15.372f);
        editor.putFloat("hk",1.149f);
        editor.apply();*/
        dol=sp.getFloat("dol",0.0f);
        eur=sp.getFloat("eur",0.0f);
        jap=sp.getFloat("jap",0.0f);
        hk=sp.getFloat("hk",0.0f);
        /* dol=0.147;
        eur=0.125;
        jap=15.372;
        hk=1.149;
        */
    }
    //在xml中btn绑定事件，四个按钮可以绑定一个事件，再依次判断
    public void btn(View btn){
        edit = findViewById(R.id.editText);
        text=findViewById(R.id.textView3);

        if(edit.getText().toString().equals("")){
               Toast.makeText(this,"pls enter a number",Toast.LENGTH_SHORT).show();
        }
        else if(btn.getId()==R.id.button){
               double n=Double.parseDouble(edit.getText().toString());
               n=n*dol;
               String s="after transform "+String.format("%.4f",n).toString()+" 美元";
               text.setText(s);
        }
        else if(btn.getId()==R.id.button2){
               double n2=Double.parseDouble(edit.getText().toString());
               n2=n2*eur;
               String s2="after transform "+String.format("%.2f",n2).toString()+" 欧元";
               text.setText(s2);
        }
        else if(btn.getId()==R.id.button3){
               double n3=Double.parseDouble(edit.getText().toString());
               n3=n3*jap;
               String s3="after transform "+String.format("%.2f",n3).toString()+" 日元";
               text.setText(s3);
        }
        else if (btn.getId() == R.id.button4) {
                double n4=Double.parseDouble(edit.getText().toString());
                n4=n4*hk;
                String s4="after transform "+String.format("%.2f",n4).toString()+" 港币";
                text.setText(s4);
        }


    }
    public void config(View v){
        Intent in = new Intent(this,Main2Activity.class);
        /*in.putExtra("dol",dol);
        in.putExtra("eur",eur);
        in.putExtra("jap",jap);
        in.putExtra("hk",hk);

        */
        Log.i(TAG,"config: ok");
        startActivityForResult(in,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==4){
          /*  Bundle bdl=data.getExtras();
            dol=bdl.getDouble("dol",1.0f);
            eur=bdl.getDouble("eur",1.0f);
            jap=bdl.getDouble("jap",1.0f);
            hk=bdl.getDouble("hk",1.0f);

*/
            dol=sp.getFloat("dol",0.0f);
            eur=sp.getFloat("eur",0.0f);
            jap=sp.getFloat("jap",0.0f);
            hk=sp.getFloat("hk",0.0f);
          Log.i(TAG,"dol:"+dol);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Toast.makeText(this,"i am about",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.settings) {
            Toast.makeText(this,"i am settings",Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}

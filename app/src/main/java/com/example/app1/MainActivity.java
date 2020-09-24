package com.example.app1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double dol,eur,jap,hk;
    TextView edit,text;
    private  static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dol=0.147;
        eur=0.125;
        jap=15.372;
        hk=1.149;

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
        in.putExtra("dol",dol);
        in.putExtra("eur",eur);
        in.putExtra("jap",jap);
        in.putExtra("hk",hk);
        Log.i(TAG,"config: ok");
        startActivityForResult(in,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==4){
            Bundle bdl=data.getExtras();
            dol=bdl.getDouble("dol",1.0f);
            eur=bdl.getDouble("eur",1.0f);
            jap=bdl.getDouble("jap",1.0f);
            hk=bdl.getDouble("hk",1.0f);
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

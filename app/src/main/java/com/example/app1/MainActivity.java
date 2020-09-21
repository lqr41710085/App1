package com.example.app1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1=findViewById(R.id.button);
        Button bt2=findViewById(R.id.button2);
        Button bt3=findViewById(R.id.button3);
        Button bt4=findViewById(R.id.button4);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView edit = findViewById(R.id.editText);
                TextView text=findViewById(R.id.textView3);
                double n=Double.parseDouble(edit.getText().toString());
                n=n*0.147;
                String s="转换后为 "+String.format("%.4f",n).toString()+" 美元";
                text.setText(s);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView edit = findViewById(R.id.editText);
                TextView text=findViewById(R.id.textView3);
                double n2=Double.parseDouble(edit.getText().toString());
                n2=n2*0.125;
                String s2="转换后为 "+String.format("%.2f",n2).toString()+" 欧元";
                text.setText(s2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView edit = findViewById(R.id.editText);
                TextView text=findViewById(R.id.textView3);
                double n3=Double.parseDouble(edit.getText().toString());
                n3=n3*15.372;
                String s3="转换后为 "+String.format("%.2f",n3).toString()+" 日元";
                text.setText(s3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView edit = findViewById(R.id.editText);
                TextView text=findViewById(R.id.textView3);
                double n4=Double.parseDouble(edit.getText().toString());
                n4=n4*1.149;
                String s4="转换后为 "+String.format("%.2f",n4).toString()+" 港币";
                text.setText(s4);
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

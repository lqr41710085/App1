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

        Button btn1= findViewById(R.id.button1);
        Button btn2= findViewById(R.id.button2);
        Button btn3= findViewById(R.id.button3);
        Button btn4= findViewById(R.id.button4);
        Button btn5= findViewById(R.id.button5);
        Button btn6= findViewById(R.id.button6);
        Button reset= findViewById(R.id.reset);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade=findViewById(R.id.grades);
                String gra=grade.getText().toString();
                grade.setText(String.valueOf(Integer.valueOf(gra)+3));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade=findViewById(R.id.grades);
                String gra=grade.getText().toString();
                grade.setText(String.valueOf(Integer.valueOf(gra)+2));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade=findViewById(R.id.grades);
                String gra=grade.getText().toString();
                grade.setText(String.valueOf(Integer.valueOf(gra)+1));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade=findViewById(R.id.grades2);
                String gra=grade.getText().toString();
                grade.setText(String.valueOf(Integer.valueOf(gra)+3));
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade=findViewById(R.id.grades2);
                String gra=grade.getText().toString();
                grade.setText(String.valueOf(Integer.valueOf(gra)+2));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade=findViewById(R.id.grades2);
                String gra=grade.getText().toString();
                grade.setText(String.valueOf(Integer.valueOf(gra)+1));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView grade1=findViewById(R.id.grades);
                TextView grade2=findViewById(R.id.grades2);
                grade1.setText("0");
                grade2.setText("0");
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

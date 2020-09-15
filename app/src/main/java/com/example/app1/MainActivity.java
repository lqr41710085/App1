package com.example.app1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        Button btn= findViewById(R.id.button);

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView ss=findViewById(R.id.number);
                String s=ss.getText().toString();
                double number=Double.valueOf(s);
                TextView result=findViewById(R.id.result);
                double re=(number-32)*1.8;
                result.setText("我是setonclick");

            }
        });
    }
    public void btnclick(View view){
        TextView ss=findViewById(R.id.number);
        String s=ss.getText().toString();
        double number=Double.valueOf(s);
        TextView result=findViewById(R.id.result);
        double re=(number-32)*1.8;
        result.setText("我是onclick");
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

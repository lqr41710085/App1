package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {
    private  static final String TAG="CalcActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Intent v=getIntent();
        String name=v.getStringExtra("name");
        final Float rate=Float.valueOf(v.getStringExtra("rate"));
        Log.i(TAG,"hhhname"+":"+rate);

        TextView nametext=findViewById(R.id.name);
        final TextView numtext=findViewById(R.id.num);
        final TextView resulttext=findViewById(R.id.reslut);
        nametext.setText(name);
        resulttext.setText("转换结果");
        numtext.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                 Float num=Float.valueOf(numtext.getText().toString());
                 Float result=num*rate;
                 resulttext.setText(result.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

    }
}

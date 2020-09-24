package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    TextView dol_text,eur_text,jap_text,hk_text;
    private static final String TAG="Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent v=getIntent();
        double dol= v.getDoubleExtra("dol",0.0f);
        double eur=v.getDoubleExtra("eur",0.0f);
        double jap=v.getDoubleExtra("jap",0.0f);
        double hk=v.getDoubleExtra("hk",0.0f);
        dol_text=findViewById(R.id.dol);
        eur_text=findViewById(R.id.eur);
        jap_text=findViewById(R.id.jap);
        hk_text=findViewById(R.id.hk);

        dol_text.setText(String.valueOf(dol));
        eur_text.setText(String.valueOf(eur));
        jap_text.setText(String.valueOf(jap));
        hk_text.setText(String.valueOf(hk));

    }
    public void save(View v){
        Intent in = getIntent();

        dol_text=findViewById(R.id.dol);
        eur_text=findViewById(R.id.eur);
        jap_text=findViewById(R.id.jap);
        hk_text=findViewById(R.id.hk);

        Double dol=Double.parseDouble(dol_text.getText().toString());
        Double eur=Double.parseDouble(eur_text.getText().toString());
        Double jap=Double.parseDouble(jap_text.getText().toString());
        Double hk=Double.parseDouble(hk_text.getText().toString());

        Bundle bdl=new Bundle();
        bdl.putDouble("dol",dol);
        bdl.putDouble("eur",eur);
        bdl.putDouble("jap",jap);
        bdl.putDouble("hk",hk);

        in.putExtras(bdl);
        setResult(4,in);
        finish();

        Log.i(TAG,"save: dol:"+dol);
    }
}

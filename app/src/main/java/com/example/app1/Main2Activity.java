package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    TextView dol_text,eur_text,jap_text,hk_text;
    private static final String TAG="Main2Activity";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sp=this.getSharedPreferences("rate",MODE_PRIVATE);
        Intent v=getIntent();
        /*
        double dol= v.getDoubleExtra("dol",0.0f);
        double eur=v.getDoubleExtra("eur",0.0f);
        double jap=v.getDoubleExtra("jap",0.0f);
        double hk=v.getDoubleExtra("hk",0.0f);
        */
        float dol=sp.getFloat("dol",0.0f);
        float eur=sp.getFloat("eur",0.0f);
        float jap=sp.getFloat("jap",0.0f);
        float hk=sp.getFloat("hk",0.0f);
        dol_text=findViewById(R.id.dol);
        eur_text=findViewById(R.id.eur);
        jap_text=findViewById(R.id.jap);
        hk_text=findViewById(R.id.hk);

        dol_text.setText(String.format("%.3f",dol).toString());
        eur_text.setText(String.format("%.3f",eur).toString());
        jap_text.setText(String.format("%.3f",jap).toString());
        hk_text.setText(String.format("%.3f",hk).toString());

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

       sp=this.getSharedPreferences("rate",MODE_PRIVATE);
        editor=sp.edit();
        editor.putFloat("dol",dol.floatValue());
        editor.putFloat("eur",eur.floatValue());
        editor.putFloat("jap",jap.floatValue());
        editor.putFloat("hk",hk.floatValue());
        editor.apply();

       /* Bundle bdl=new Bundle();
        bdl.putDouble("dol",dol);
        bdl.putDouble("eur",eur);
        bdl.putDouble("jap",jap);
        bdl.putDouble("hk",hk);

        in.putExtras(bdl);*/
        setResult(4,in);
        finish();

        Log.i(TAG,"save: dol:"+dol);
    }
}

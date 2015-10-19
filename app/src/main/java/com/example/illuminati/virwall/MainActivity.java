package com.example.illuminati.virwall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bEditList = (Button)findViewById(R.id.lEditList);
        Button bGoShopping = (Button) findViewById(R.id.lGoShopping);
        bEditList.setOnClickListener(this);
        bGoShopping.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d("startitn", "inview");
        Intent i;
        switch (view.getId()){
            case R.id.lEditList:
                i = new Intent("com.example.illuminati.virwall.LISTMANAGEMENT");
                Log.d("startitn", "started");
                startActivity(i);

                break;
            case R.id.lGoShopping:
                i = new Intent("com.example.illuminati.virwall.SHOPPING");
                startActivity(i);
                break;
            default:
        }
    }
}

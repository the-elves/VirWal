package com.example.illuminati.virwall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by illuminati on 17/10/15.
 */
public class Shopping extends Activity {
    public ArchitectView architectView;
    ArrayList<String> shoppingItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shoppingItems = new ArrayList<String >();
        setContentView(R.layout.activity_shopping);
        architectView = (ArchitectView)findViewById(R.id.lARView);
        final StartupConfiguration config = new StartupConfiguration("sfaQNOG38iiDbhTWHlPHaxx3CvHez6fPbIrobTt58z1zIKIZvXfmHPdwLVCnKACb+kXrD3nMKMwKStmGNygx2e5eV0liQ18lnfnuD5XbVwYzDMVdDhezRE70dWh5eUXuKx8f9ZBHJs7x1W1cdftT63NRqa9VcOmvCS34QAYtt5FTYWx0ZWRfX4H7ZhivU/cQtM21dUZO3V4Ix1q8yGL8vdcvrxykUhZxG+u3WJvC6R55i3PvJUWstpQ2RqbG0/3T91yLxtHp7TeSo7Co81Kg7yBbfFLlZXh+mZKEfC9PwqvqI4hvQx4mHamAbizXATieEo+i4UlxXGTl7lR3PChB9grfzDYE+H+9xDEqTj41MED2Cqf7WiwxppynwHvoidU+kOViMZ5KBuJ2RMcBhdR4nRtt+qEl76tU99owx5ttqtYL+3wdfculDaJVVJxSQ3+qAV7XqG2P8UoL8WBS/m6gWRxwORvZE54PrHE9V0s67xlhaXT/myWl+8UY5ebAzNJ6Vq1rPyye9Bt+Uuh1W8+bUS/xzikPIjCOXcfKrKwiKj5/jeNeAyyKL5IeAVzHFH1/my4g8pAb9k+tYdwg+N9n1hPO7X8YKdcGFxhNfnng07U9QuZAPeTgzBl014ga0Q+MTg9ZhwqPmjuE3VbuezR5Uh9VrUo7pi8igW6D7Vp/Gkd682eMpa6cOsovOAWoMlc2");
        architectView.onCreate(config);
//        populateShoppingList();

    }
    protected void populateShoppingList(){
        SharedPreferences pref = getSharedPreferences("List_Items",MODE_PRIVATE);
        Map<String, ?> allPref= pref.getAll();
        Iterator itr = allPref.entrySet().iterator();
        while(itr.hasNext()){
            shoppingItems.add((String)((Map.Entry)itr).getValue());
        }

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();
        try {
            this.architectView.load("arexp.html");
            String jsString = "setShoppingListArray(\"blueCap;RedCap;airtel\")";
            architectView.callJavascript(jsString);
            Log.d("architect", "loaded");

        } catch (IOException e) {
            Log.d("architect","exception",e);
            e.printStackTrace();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume();

    }


}

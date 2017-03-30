package com.learn.dynotes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends Activity {

    public static final String PREF_NAME="LoginDetails";
    SharedPreferences logindata=getSharedPreferences(loginActivity.PREF_NAME,0);
    Intent mainIntent=new Intent(loginActivity.this,MainActivity.class);

    static int attempts=3;
    String password="";
    EditText passBox;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ( logindata.getBoolean("isPassSet",false)){

            startActivity(mainIntent);

        }else{
            setContentView(R.layout.activity_login);




        }

    }


    public void login(View v){

        passBox=(EditText) this.findViewById(R.id.passBox);
        password=passBox.getText().toString();

        if (logindata.getString("pass","").equals(password)){
            startActivity(mainIntent);
        }else{
            attempts--;
        }

    }

}

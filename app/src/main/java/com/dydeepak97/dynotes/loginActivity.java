package com.dydeepak97.dynotes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends Activity {

    public static final String PREF_NAME="LoginDetails";


    static int attempts=3;
    String password="";
    EditText passBox;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        decide();

        passBox=(EditText) this.findViewById(R.id.passBox);
        passBox.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE){
                    login(log);
                    return true;
                }
                return false;
            }

        } );

    }

    public void decide(){
        SharedPreferences logindata= getSharedPreferences(loginActivity.PREF_NAME, 0);

        if ( logindata.getBoolean("isPassNotSet",true)){
            Intent mainIntent=new Intent(loginActivity.this,MainActivity.class);
            startActivity(mainIntent);


        }
    }



    public void login(View v){





        password=passBox.getText().toString();
        SharedPreferences logindata= getSharedPreferences(loginActivity.PREF_NAME, 0);

        if (logindata.getString("pass","").equals(password)){
            Intent mainIntent=new Intent(loginActivity.this,MainActivity.class);
            startActivity(mainIntent);
        }else{
            Toast.makeText(this,"Wrong Password",Toast.LENGTH_SHORT).show();
            attempts--;
        }

    }

}

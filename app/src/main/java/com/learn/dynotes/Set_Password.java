package com.learn.dynotes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Set_Password extends AppCompatActivity {

    SharedPreferences logindata=getSharedPreferences(loginActivity.PREF_NAME,0);
    EditText newPass;
    String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__password);


    }


    public void setNewPass(View view){
        newPass=(EditText) findViewById(R.id.newPass);
        newPassword=newPass.getText().toString();

        SharedPreferences.Editor editor = logindata.edit();

        editor.putString("pass",newPassword);
        editor.putBoolean("isPassSet",true);

        editor.commit();


    }


}


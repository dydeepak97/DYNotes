package com.dydeepak97.dynotes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Set_Password extends AppCompatActivity {


    EditText newPass;
    String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__password);


    }


    public void setNewPass(View view){
        SharedPreferences logindata=getSharedPreferences(loginActivity.PREF_NAME,0);

        newPass=(EditText) findViewById(R.id.newPass);
        newPassword=newPass.getText().toString();
        if(newPassword.isEmpty()){

            Toast.makeText(this,"Password Changed!",Toast.LENGTH_SHORT).show();
        }else {

            SharedPreferences.Editor editor = logindata.edit();

            editor.putString("pass", newPassword);
            editor.putBoolean("isPassNotSet", false);

            editor.commit();

            Toast.makeText(this, "Password Changed!", Toast.LENGTH_SHORT).show();
            this.onBackPressed();
        }

    }


}


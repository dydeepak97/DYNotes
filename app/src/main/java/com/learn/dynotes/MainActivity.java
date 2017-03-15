package com.learn.dynotes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    FloatingActionButton addNote;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote = (FloatingActionButton) findViewById(R.id.addNote);


        databaseHandler= new DatabaseHandler(this);



    }


    public void add_but(View v){
        Intent myIntent= new Intent(MainActivity.this,EditNoteActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("source","add");

        myIntent.putExtras(bundle);

        startActivity(myIntent);

    }
}

package com.dydeepak97.dynotes;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.ListItemClickListener{

    FloatingActionButton addNote;
    DatabaseHandler databaseHandler;
    public static NotesAdapter notesAdapter;
    List<Note> noteList;
    RecyclerView noteListVIew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote = (FloatingActionButton) findViewById(R.id.addNote);


        databaseHandler= new DatabaseHandler(this);
        noteList = databaseHandler.getAllNotes();

        notesAdapter = new NotesAdapter(this, noteList, this);

        noteListVIew = (RecyclerView) findViewById(R.id.notesRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        noteListVIew.setLayoutManager(layoutManager);

        try{
            noteListVIew.setAdapter(notesAdapter);
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        /*

        noteListVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        */

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {



                Intent editIntent= new Intent(getBaseContext(), EditNoteActivity.class);
                Bundle bundle= new Bundle();

                Note note= noteList.get(clickedItemIndex);

                bundle.putString("source","editPress");
                bundle.putString("noteTitle", note.getTitle());
                bundle.putString("noteDescription",note.getDescription());
                bundle.putInt("noteId",note.getId());

                editIntent.putExtras(bundle);

                this.startActivity(editIntent);



    }

    public void add_but(View v){
        Intent myIntent= new Intent(MainActivity.this,EditNoteActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("source","add");

        myIntent.putExtras(bundle);

        startActivity(myIntent);

    }

    public void lock(View v){
        Intent myIntent= new Intent(MainActivity.this,Set_Password.class);


        startActivity(myIntent);

    }
}

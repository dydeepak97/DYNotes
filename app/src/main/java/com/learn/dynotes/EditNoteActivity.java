package com.learn.dynotes;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EditNoteActivity extends Activity implements View.OnClickListener{

    FloatingActionButton save,cancel;
    String title,content;
    EditText titleBox,contentBox;
    int noteId;
    Boolean isUpdateMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        isUpdateMode=false;

        titleBox=(EditText) this.findViewById(R.id.title_edit);
        contentBox=(EditText) this.findViewById(R.id.content_edit);

        save=(FloatingActionButton) this.findViewById(R.id.fab_save);
        save.setOnClickListener(this);

        cancel=(FloatingActionButton) this.findViewById(R.id.fab_delete);
        cancel.setOnClickListener(this);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("source")){

            if(bundle.getString("source").equalsIgnoreCase("editPress")) {
                isUpdateMode = true;
                noteId = bundle.getInt("noteId");
                titleBox.setText(bundle.getString("noteTitle"));
                contentBox.setText(bundle.getString("noteDescription"));
                cancel.setVisibility(View.VISIBLE);
            } else if(bundle.getString("source").equalsIgnoreCase("add")){
                isUpdateMode=false;
                cancel.setVisibility(View.GONE);

            }else {
                Toast.makeText(this,"Invalid Parameters",Toast.LENGTH_LONG).show();
                super.onBackPressed();
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_save:
                saveNote();
                break;
            case R.id.fab_delete:
                deleteNote();
                break;
        }
    }

    private void saveNote(){
        title=titleBox.getText().toString();
        content=contentBox.getText().toString();

        if(!isValidNote()) {
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setDescription(content);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        if (!isUpdateMode) {

            databaseHandler.addNote(note);
            Toast.makeText(this,"Note Added" ,Toast.LENGTH_LONG).show();
        }
        else{
            note.setId(noteId);
            databaseHandler.updateNote(note);
            Toast.makeText(this,"Note Updated",Toast.LENGTH_LONG).show();
        }

        List<Note> notes   = databaseHandler.getAllNotes();
        MainActivity.notesAdapter.clear();
        MainActivity.notesAdapter.addAll(notes);
        MainActivity.notesAdapter.notifyDataSetChanged();

        super.onBackPressed();

    }



    private void deleteNote(){

        
    }

    private Boolean isValidNote(){
    return  true;
    }

}

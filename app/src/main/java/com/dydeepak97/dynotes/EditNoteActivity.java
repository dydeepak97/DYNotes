package com.dydeepak97.dynotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete Note")
                .setMessage("Are You sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler databaseHandler= new DatabaseHandler(EditNoteActivity.this);

                        databaseHandler.deleteNote(String.valueOf(noteId));

                        List<Note> notes= databaseHandler.getAllNotes();
                        MainActivity.notesAdapter.clear();
                        MainActivity.notesAdapter.addAll(notes);
                        MainActivity.notesAdapter.notifyDataSetChanged();

                        Toast.makeText(EditNoteActivity.this,"Note Deleted",Toast.LENGTH_SHORT).show();
                        EditNoteActivity.this.onBackPressed();


                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

    private Boolean isValidNote(){
        if(title.isEmpty()& content.isEmpty()){
            Toast.makeText(this,"Enter Title & Description",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(title.isEmpty()){
            Toast.makeText(this,"Enter Title",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(content.isEmpty()){
            Toast.makeText(this,"Enter Description",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

}

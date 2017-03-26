package com.learn.dynotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;



public class NotesAdapter extends ArrayAdapter {

    private List<Note> noteList;
    private Context context;

    public NotesAdapter(Context context, List<Note> noteList) {
        super(context, R.layout.notes_view_item , noteList );
        this.noteList=noteList;
        this.context=context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View v= convertView;

        if(v==null){
            LayoutInflater v1=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=v1.inflate(R.layout.notes_view_item,null);
        }

        final Note note= noteList.get(position);

        if (note != null){

            TextView title = (TextView) v.findViewById(R.id.title);
            TextView description= (TextView) v.findViewById(R.id.content);
            if(title != null){
                title.setText(note.getTitle());

            }
         if (description != null){
             description.setText(note.getDescription());
         }
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent= new Intent(context,EditNoteActivity.class);
                Bundle bundle= new Bundle();

                Note note= noteList.get(position);

                bundle.putString("source","editPress");
                bundle.putString("noteTtile", note.getTitle());
                bundle.putString("noteDescription",note.getDescription());
                bundle.putString("noteId",""+ note.getId());

                editIntent.putExtras(bundle);

                context.startActivity(editIntent);
            }
        });

        return v;



    }


}

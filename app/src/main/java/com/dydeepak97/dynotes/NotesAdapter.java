package com.dydeepak97.dynotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;



public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private Context context;

    public NotesAdapter (Context context, List<Note> noteList){
        this.noteList = noteList;
        this.context= context;
    }

//    public NotesAdapter(Context context, List<Note> noteList) {
//        super(context, R.layout.notes_view_item , noteList );
//        this.noteList=noteList;
//        this.context=context;
//    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;

        public NoteViewHolder ( View itemView ){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description= (TextView) itemView.findViewById(R.id.content);
        }

        void bind( int listIndex ){

            Note note = noteList.get(listIndex);

            title.setText(note.getTitle());
            description.setText(note.getDescription());

        }
    }



    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListner = R.layout.notes_view_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListner, parent , shouldAttachToParentImmediately);

        NoteViewHolder viewHolder = new NoteViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    /*

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
                bundle.putString("noteTitle", note.getTitle());
                bundle.putString("noteDescription",note.getDescription());
                bundle.putInt("noteId",note.getId());

                editIntent.putExtras(bundle);

                context.startActivity(editIntent);
            }
        });

        return v;



    }

    */


}

package com.hedgehog.note.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.note.R;
import com.hedgehog.note.bean.Note;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hedge_hog on 16/3/1.
 */
public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Bind(R.id.text_note)
    TextView textNote;
    private Context mContext;
    private List<Note> listNote;

    public NoteAdapter(Context mContext, List<Note> listNote) {
        this.mContext = mContext;
        this.listNote = listNote;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NoteViewHolder viewHolder = new NoteViewHolder(LayoutInflater.
                from(mContext).inflate(R.layout.recycler_note, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoteViewHolder viewholder = (NoteViewHolder) holder;

        viewholder.textNote.setText(listNote.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    /**
     *
     */
    class NoteViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_note)
        TextView textNote;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

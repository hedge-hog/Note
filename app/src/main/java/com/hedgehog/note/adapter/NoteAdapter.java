package com.hedgehog.note.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.note.R;
import com.hedgehog.note.bean.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hedge_hog on 16/3/1.
 */
public class NoteAdapter extends BaseRecyclerviewAdapter<Note> {

    private Context mContext;
    private List<Note> listNote;

    public NoteAdapter(Context mContext, List<Note> list) {
        super(mContext, list);
        this.mContext = mContext;
        this.listNote = new ArrayList<>(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NoteViewHolder viewHolder = new NoteViewHolder(LayoutInflater.
                from(mContext).inflate(R.layout.recycler_note, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        NoteViewHolder viewholder = (NoteViewHolder) holder;

        viewholder.noteContent.setText(listNote.get(position).getText());
        viewholder.noteTitle.setText(listNote.get(position).getComment());
        viewholder.noteTime.setText(listNote.get(position).getDate() + "");

    }

    @Override
    public void setList(List<Note> list) {
        super.setList(list);
        this.listNote.clear();
        this.listNote.addAll(list);
        notifyDataSetChanged();
    }

    /**
     *
     */
    class NoteViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.note_content)
        TextView noteContent;
        @Bind(R.id.note_title)
        TextView noteTitle;
        @Bind(R.id.note_time)
        TextView noteTime;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

package com.hedgehog.note.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hedgehog.note.R;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.util.CustomDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hedge_hog on 16/3/1.
 */
public class NoteAdapter extends BaseRecyclerviewAdapter<Note> {

    private Context mContext;
    private ArrayList<Note> listNote;

    public NoteAdapter(Context mContext, ArrayList<Note> list) {
        super(mContext, list);
        this.mContext = mContext;
        this.listNote = list;
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

        viewholder.noteContent.setText(listNote.get(position).getContent());
        viewholder.noteTitle.setText(listNote.get(position).getTitle());


        if (TextUtils.isEmpty(viewholder.noteTitle.getText())) {
            viewholder.relNoteTitle.setVisibility(View.GONE);
        }

        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            viewholder.noteTime.setText(CustomDateFormat.format(sfd.format(listNote.get(position).getDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setList(ArrayList<Note> list) {
        super.setList(list);
        if (this.listNote == null || this.listNote.size() == 0) {
            this.listNote = new ArrayList<>(list);
            notifyDataSetChanged();
        } else {
            this.listNote.addAll(list);
            notifyDataSetChanged();
        }
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.note_content)
        TextView noteContent;
        @Bind(R.id.note_title)
        TextView noteTitle;
        @Bind(R.id.note_time)
        TextView noteTime;
        @Bind(R.id.rel_note_title)
        RelativeLayout relNoteTitle;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

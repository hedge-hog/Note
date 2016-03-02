package com.hedgehog.note.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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

        viewholder.noteContent.setText(listNote.get(position).getText());
        viewholder.noteTitle.setText(listNote.get(position).getComment());
        viewholder.noteTime.setText(listNote.get(position).getDate() + "");

        viewholder.noteMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, v);
                popupMenu.getMenuInflater().inflate(R.menu.item_popu_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    public void addList(List<Note> listNote) {
        this.listNote.addAll(listNote);
        notifyDataSetChanged();
    }

    public void clear() {
        this.listNote.clear();
    }

    @Override
    public int getItemCount() {
        return listNote.size();
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
        @Bind(R.id.note_more)
        ImageButton noteMore;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

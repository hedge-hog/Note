package com.hedgehog.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.hedgehog.note.R;
import com.hedgehog.note.adapter.BaseRecyclerviewAdapter;
import com.hedgehog.note.adapter.NoteAdapter;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.dao.NoteDao;
import com.hedgehog.note.event.NotifyEvent;
import com.hedgehog.note.ui.BaseApplication;
import com.hedgehog.note.ui.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.dao.query.Query;
import de.greenrobot.event.EventBus;

public class SearchActivity extends BaseActivity {

    @Bind(R.id.recy_note)
    RecyclerView mRecyNote;
    @Bind(R.id.text_search_none)
    TextView textSearchNone;

    NoteAdapter noteAdapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initToolbar("搜索结果");
        EventBus.getDefault().register(SearchActivity.this);


        String noteText = getIntent().getStringExtra("noteText");

        Query query = getNoteDao().queryBuilder()
//              .where(NoteDao.Properties.Title.like("%" + noteText + "%"))
                .where(NoteDao.Properties.Content.like("%" + noteText + "%"))
                .orderAsc(NoteDao.Properties.Date)
                .build();
        notes = query.list();

        if (notes.size() == 0) {
            textSearchNone.setVisibility(View.VISIBLE);
        } else {
            initAdapter(notes);
        }
    }

    /**
     * @param event
     */
    public void onEventMainThread(NotifyEvent event) {
        switch (event.getType()) {
            case NotifyEvent.DEL_NOTE:
                finish();
                break;
        }
    }


    private void initAdapter(final List<Note> notes) {
        noteAdapter = new NoteAdapter(SearchActivity.this, notes);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyNote.setLayoutManager(staggeredGridLayoutManager);
        mRecyNote.setAdapter(noteAdapter);

        noteAdapter.setOnInViewClickListener(R.id.note_content_ll, new BaseRecyclerviewAdapter.onInternalClickListenerImpl<Note>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, Note values) {
                super.OnClickListener(parentV, v, position, values);
                Long id = notes.get(position).getId();
                Intent i = new Intent(SearchActivity.this, NoteDetailActivity.class);
                i.putExtra("noteId", id);
                startActivity(i);
            }
        });
    }

    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(SearchActivity.this);
    }
}

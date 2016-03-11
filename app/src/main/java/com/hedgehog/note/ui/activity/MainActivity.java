package com.hedgehog.note.ui.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hedgehog.note.R;
import com.hedgehog.note.adapter.BaseRecyclerviewAdapter;
import com.hedgehog.note.adapter.NoteAdapter;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.dao.NoteDao;
import com.hedgehog.note.ui.BaseApplication;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.dao.query.Query;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recy_note)
    RecyclerView recyclerNote;
    @Bind(R.id.fab_add_note)
    FloatingActionButton fabAddNote;

    private Toolbar mToolbar;
    NoteAdapter noteAdapter;
    List<Note> notes;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("迷你账本");
        setSupportActionBar(mToolbar);

        query = getNoteDao().queryBuilder()
                .orderDesc(NoteDao.Properties.Date)
                .build();
        notes = query.list();

        noteAdapter = new NoteAdapter(MainActivity.this, notes);

//      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerNote.setLayoutManager(staggeredGridLayoutManager);
        recyclerNote.setAdapter(noteAdapter);


        /**
         *  BaseAdapter的内部定义的接口,将recyclerview内部item的点击事件转移到Adapter外部
         */
        noteAdapter.setOnInViewClickListener(R.id.note_more,
                new BaseRecyclerviewAdapter.onInternalClickListenerImpl<Note>() {
                    @Override
                    public void OnClickListener(View parentV, View v, final Integer position, Note values) {
                        super.OnClickListener(parentV, v, position, values);

                        PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                        popupMenu.getMenuInflater().inflate(R.menu.item_popu_menu, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case R.id.pop_del:
                                        delNote(notes.get(position).getId());
                                        break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });


        noteAdapter.setOnInViewClickListener(R.id.note_content_ll, new BaseRecyclerviewAdapter.onInternalClickListenerImpl<Note>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, Note values) {
                super.OnClickListener(parentV, v, position, values);

                startActivity(new Intent(MainActivity.this, NoteDetailActivity.class));
            }
        });
    }

    @OnClick(R.id.fab_add_note)
    protected void addNote(View v) {
        switch (v.getId()) {
            case R.id.fab_add_note:
                startActivity(new Intent(this, AddNoteActivity.class));
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
    }

    private SQLiteDatabase getDb() {
        return ((BaseApplication) this.getApplicationContext()).getDb();
    }




    /**
     * 删除日记
     */
    private void delNote(long id) {

        getNoteDao().deleteByKey(id);

        query = getNoteDao().queryBuilder().build();
        notes = query.list();

        noteAdapter.setList(notes);
    }

    /**
     * 搜索日记
     */
//    private void queryNote() {
//
//        String noteText = editAdd.getText().toString();
//        editAdd.setText("");
//        if (TextUtils.isEmpty(noteText)) {
//            Toast.makeText(this, "请先输入文字!", Toast.LENGTH_SHORT).show();
//        } else {
//            Query query = getNoteDao().queryBuilder()
//                    .where(NoteDao.Properties.Text.like("%" + noteText + "%"))
//                    .orderAsc(NoteDao.Properties.Date)
//                    .build();
//            // 查询结果以 List 返回
//            notes = query.list();
//            noteAdapter.setList(notes);
//        }
//        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
//        QueryBuilder.LOG_SQL = true;
//        QueryBuilder.LOG_VALUES = true;
//
//    }

}

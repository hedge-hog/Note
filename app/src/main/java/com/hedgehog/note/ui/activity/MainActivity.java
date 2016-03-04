package com.hedgehog.note.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hedgehog.note.R;
import com.hedgehog.note.adapter.BaseRecyclerviewAdapter;
import com.hedgehog.note.adapter.NoteAdapter;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.dao.NoteDao;
import com.hedgehog.note.ui.BaseApplication;
import com.hedgehog.note.ui.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class MainActivity extends BaseActivity {

    @Bind(R.id.edit_add)
    EditText editAdd;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.recy_note)
    RecyclerView recyclerNote;

    private Cursor cursor;
    NoteAdapter noteAdapter;
    List<Note> notes;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar("SQLtest");


        query = getNoteDao().queryBuilder().build();
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


    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
    }

    private SQLiteDatabase getDb() {
        return ((BaseApplication) this.getApplicationContext()).getDb();
    }

    @OnClick({R.id.btn_add, R.id.btn_search})
    protected void btnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                queryNote();
                break;
            case R.id.btn_add:
                addNote();
                break;
        }
    }

    /**
     * 添加笔记
     */
    private void addNote() {

        String noteText = editAdd.getText() + "";
        editAdd.setText("");

        SimpleDateFormat smf = new SimpleDateFormat("yyyy年MM月dd日");
        String comment = smf.format(new Date());

        if (TextUtils.isEmpty(noteText)) {
            Toast.makeText(this, "请先输入文字!", Toast.LENGTH_SHORT).show();
        } else {
//          向数据库里增加数据
            Note note = new Note(null, noteText, comment, new Date());
            getNoteDao().insert(note);


            query = getNoteDao().queryBuilder().build();
            notes = query.list();


            noteAdapter.setList(notes);
        }
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
    private void queryNote() {

        String noteText = editAdd.getText().toString();
        editAdd.setText("");
        if (TextUtils.isEmpty(noteText)) {
            Toast.makeText(this, "请先输入文字!", Toast.LENGTH_SHORT).show();
        } else {
            Query query = getNoteDao().queryBuilder()
//                    .where(NoteDao.Properties.Text.eq(noteText))
                    .where(NoteDao.Properties.Text.like("%" + noteText + "%"))
                    .orderAsc(NoteDao.Properties.Date)
                    .build();
            // 查询结果以 List 返回
            notes = query.list();
            noteAdapter.setList(notes);
        }
        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

    }

    /**
     * 修改日记
     */
    private void modifyNote() {

    }


}

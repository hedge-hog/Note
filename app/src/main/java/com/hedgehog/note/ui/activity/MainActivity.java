package com.hedgehog.note.ui.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hedgehog.note.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Query query = getNoteDao().queryBuilder().build();
        List<Note> notes = query.list();

        noteAdapter = new NoteAdapter(MainActivity.this, notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerNote.setLayoutManager(linearLayoutManager);
        recyclerNote.setAdapter(noteAdapter);

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


            noteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 删除日记
     */
    private void delNote() {

    }

    /**
     * 搜索日记
     */
    private void queryNote() {

    }

    /**
     * 修改日记
     */
    private void modifyNote() {

    }


}

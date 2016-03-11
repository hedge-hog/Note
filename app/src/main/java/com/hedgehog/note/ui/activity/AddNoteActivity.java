package com.hedgehog.note.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hedgehog.note.R;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.dao.NoteDao;
import com.hedgehog.note.ui.BaseApplication;
import com.hedgehog.note.ui.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNoteActivity extends BaseActivity {

    @Bind(R.id.edit_add_note_title)
    EditText editAddNoteTitle;
    @Bind(R.id.edit_add_note_content)
    EditText editAddNoteContent;
    @Bind(R.id.text_note_time)
    TextView textNoteTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        initToolbar("新建");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.menu.item_add_note_menu:
                addNote();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
    }

    /**
     * 添加笔记
     */
    private void addNote() {

        String noteText = editAddNoteContent.getText() + "";

        SimpleDateFormat smf = new SimpleDateFormat("yyyy年MM月dd日");
        String comment = smf.format(new Date());

        if (TextUtils.isEmpty(noteText)) {
            Toast.makeText(this, "请先输入文字!", Toast.LENGTH_SHORT).show();
        } else {
//          向数据库里增加数据
            Note note = new Note(null, noteText, comment,new Date());
            getNoteDao().insert(note);

//            query = getNoteDao().queryBuilder()
//                    .orderDesc(NoteDao.Properties.Date)
//                    .build();
//            notes = query.list();
//
//            noteAdapter.setList(notes);
        }
    }

}

package com.hedgehog.note.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import de.greenrobot.event.EventBus;

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
            case R.id.item_add_note:
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
        String noteTitle = editAddNoteTitle.getText() + "";

        if (TextUtils.isEmpty(noteText)) {
            Snackbar.make(editAddNoteContent, "你在逗我吗?写点东西再保存好吗?", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } else {
//          向数据库里增加数据
            Note note = new Note(null, noteText, noteTitle, new Date());
            getNoteDao().insert(note);
            EventBus.getDefault().post("update");
//          发送广播通知主页面进行数据的更新
            finish();

        }
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }
}

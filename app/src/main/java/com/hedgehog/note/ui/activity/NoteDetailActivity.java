package com.hedgehog.note.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hedgehog.note.R;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.dao.NoteDao;
import com.hedgehog.note.ui.BaseApplication;
import com.hedgehog.note.ui.base.BaseActivity;
import com.hedgehog.note.util.CustomDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.dao.query.Query;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class NoteDetailActivity extends BaseActivity {

    @Bind(R.id.edit_note_title)
    EditText editNoteTitle;
    @Bind(R.id.edit_note_content)
    EditText editNoteContent;
    @Bind(R.id.text_note_time)
    TextView textNoteTime;
    Long noteId;
    String noteText;
    String noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        initToolbar("笔记详情");
        noteId = getIntent().getLongExtra("noteId", 0l);
        initView(noteId);

    }

    private void initView(Long noteId) {

        hideKeyBoard(editNoteTitle);

        Query query = getNoteDao().queryBuilder()
                .where(NoteDao.Properties.Id.eq(noteId))
                .build();
        List<Note> notes = query.list();

        editNoteTitle.setText(notes.get(0).getComment());
        editNoteContent.setText(notes.get(0).getText());

        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            textNoteTime.setText("修改时间: " + CustomDateFormat.format(sfd.format(notes.get(0).getDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        editNoteTitle.addTextChangedListener(t);
        editNoteContent.addTextChangedListener(t);
    }


    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_delete_note:
                deleteNote();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private TextWatcher t = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            noteText = editNoteContent.getText() + "";
            noteTitle = editNoteTitle.getText() + "";

            if (!TextUtils.isEmpty(noteText) || !TextUtils.isEmpty(noteTitle)) {

                Note note = new Note(noteId, noteText, noteTitle, new Date());

                getNoteDao().update(note);
                EventBus.getDefault().post("update");

            }


            if (TextUtils.isEmpty(noteText) && TextUtils.isEmpty(noteTitle)) {
                getNoteDao().deleteByKey(noteId);
                EventBus.getDefault().post("update");

            }


        }

        @Override
        public void afterTextChanged(Editable s) {

            noteText = editNoteContent.getText() + "";
            noteTitle = editNoteTitle.getText() + "";

            if (TextUtils.isEmpty(noteText) && TextUtils.isEmpty(noteTitle)) {
                getNoteDao().deleteByKey(noteId);
                EventBus.getDefault().post("update");
            }

        }
    };

    private void deleteNote() {
        getNoteDao().deleteByKey(noteId);
        EventBus.getDefault().post("update");
        finish();
    }


    private void hideKeyBoard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteText = editNoteContent.getText() + "";
        noteTitle = editNoteTitle.getText() + "";

        if (TextUtils.isEmpty(noteText) && TextUtils.isEmpty(noteTitle)) {
            getNoteDao().deleteByKey(noteId);
            EventBus.getDefault().post("update");
        }

    }
}

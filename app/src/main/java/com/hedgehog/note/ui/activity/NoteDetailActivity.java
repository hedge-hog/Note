package com.hedgehog.note.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hedgehog.note.R;
import com.hedgehog.note.bean.Note;
import com.hedgehog.note.dao.NoteDao;
import com.hedgehog.note.event.NotifyEvent;
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

public class NoteDetailActivity extends BaseActivity {

    @Bind(R.id.edit_note_title)
    EditText editNoteTitle;
    @Bind(R.id.edit_note_content)
    EditText editNoteContent;
    @Bind(R.id.text_note_time)
    TextView textNoteTime;

    private Long noteId;
    private String noteText;
    private String noteTitle;

    private NotifyEvent<Note> event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        initToolbar("笔记详情");
        noteId = getIntent().getLongExtra("noteId", 0l);
        event = new NotifyEvent<>();
        initView(noteId);

    }

    private void initView(Long noteId) {
        Query query = getNoteDao().queryBuilder()
                .where(NoteDao.Properties.Id.eq(noteId))
                .build();
        List<Note> notes = query.list();

        editNoteTitle.setText(notes.get(0).getTitle());
        editNoteContent.setText(notes.get(0).getContent());
        editNoteTitle.setSelection(notes.get(0).getTitle().length());
        editNoteContent.setSelection(notes.get(0).getContent().length());

        editNoteContent.setOnFocusChangeListener(f);
        editNoteTitle.setOnFocusChangeListener(f);

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

    private View.OnFocusChangeListener f = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            editNoteContent.setCursorVisible(true);
            editNoteTitle.setCursorVisible(true);
        }
    };

    private TextWatcher t = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateNote();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void deleteNote() {
        getNoteDao().deleteByKey(noteId);
        event.setType(NotifyEvent.DEL_NOTE);
        EventBus.getDefault().post(event);
        finish();
    }


    private void updateNote() {
        noteText = editNoteContent.getText().toString();
        noteTitle = editNoteTitle.getText().toString();
        Note note = new Note(noteId, noteText, noteTitle, null, null, null, new Date());
        getNoteDao().update(note);
        event.setType(NotifyEvent.UPDATE_NOTE);
        EventBus.getDefault().post(event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteText = editNoteContent.getText().toString();
        noteTitle = editNoteTitle.getText().toString();
        if (TextUtils.isEmpty(noteText) && TextUtils.isEmpty(noteTitle)) {
            getNoteDao().deleteByKey(noteId);
            event.setType(NotifyEvent.DEL_NOTE);
            EventBus.getDefault().post(event);
        }
    }
}

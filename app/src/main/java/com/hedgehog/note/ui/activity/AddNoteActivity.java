package com.hedgehog.note.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import java.util.logging.Logger;

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

    private String noteText;
    private String noteTitle;
    private Long noteId;

    private NotifyEvent<Note> event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        initToolbar("新建");

        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            textNoteTime.setText("修改时间: " + CustomDateFormat.format(sfd.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event = new NotifyEvent<>();

        Note note = new Note(null, "", "", null, null, null, new Date());
        getNoteDao().insert(note);
        noteId = note.getId();

        editAddNoteTitle.addTextChangedListener(t);
        editAddNoteContent.addTextChangedListener(t);

    }


    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
    }


    private TextWatcher t = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            noteText = editAddNoteContent.getText().toString();
            noteTitle = editAddNoteTitle.getText().toString();

            addNote();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void addNote() {

        if (!TextUtils.isEmpty(noteText) || !TextUtils.isEmpty(noteTitle)) {
            Note note = new Note(noteId, noteText, noteTitle, null, null, null, new Date());
            getNoteDao().update(note);
            event.setType(NotifyEvent.CREATE_NOTE);
            EventBus.getDefault().post(event);

        }

        if (TextUtils.isEmpty(noteText) && TextUtils.isEmpty(noteTitle)) {
            getNoteDao().deleteByKey(noteId);
            event.setType(NotifyEvent.DEL_NOTE);
            EventBus.getDefault().post(event);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteText = editAddNoteContent.getText().toString();
        noteTitle = editAddNoteTitle.getText().toString();
        if (TextUtils.isEmpty(noteText) && TextUtils.isEmpty(noteTitle)) {
            getNoteDao().deleteByKey(noteId);
            event.setType(NotifyEvent.DEL_NOTE);
            EventBus.getDefault().post(event);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

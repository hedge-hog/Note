package com.hedgehog.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class NoteDetailActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edit_add)
    EditText editAdd;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);


        initToolbar("笔记详情");

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteDetailActivity.this, AboutActivity.class));
            }
        });
    }

//    @OnClick({R.id.btn_add, R.id.btn_search})
//    protected void btnClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_add:
//                addNote();
//                break;
//        }
//    }

    private NoteDao getNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
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

//          发送广播通知主页进行更新

        }
    }
}

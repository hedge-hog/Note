package com.hedgehog.note.ui.activity;

import android.os.Bundle;

import com.hedgehog.note.R;
import com.hedgehog.note.ui.base.BaseActivity;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initToolbar("关于");



    }
}

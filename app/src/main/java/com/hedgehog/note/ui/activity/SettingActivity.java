package com.hedgehog.note.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.hedgehog.note.R;
import com.hedgehog.note.ui.base.BaseActivity;
import com.hedgehog.note.ui.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initToolbar("设置");

        SettingFragment settingFragment=SettingFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.frm_setting,settingFragment).commit();
    }
}

package com.hedgehog.note.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.hedgehog.note.R;

/**
 * Created by hedge_hog on 16/3/24.
 */
public class SettingFragment extends PreferenceFragment {

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

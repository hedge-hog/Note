package com.hedgehog.note.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.hedgehog.note.R;
import com.hedgehog.note.event.NotifyEvent;
import com.hedgehog.note.ui.activity.AboutActivity;
import com.hedgehog.note.util.SnackbarUtils;

import de.greenrobot.event.EventBus;

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


    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String key = preference.getKey();
        if (TextUtils.equals(key, getString(getActivity(), R.string.preference_darktheme))) {

        }

        if (TextUtils.equals(key, getString(getActivity(), R.string.preference_feedback))) {

        }

        if (TextUtils.equals(key, getString(getActivity(), R.string.preference_rating_app))) {

        }

        if (TextUtils.equals(key, getString(getActivity(), R.string.preference_about))) {

            startActivity(new Intent(getActivity(), AboutActivity.class));

        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


    private String getString(Context context, @StringRes int string) {
        if (context != null)
            return context.getString(string);
        return "";
    }


}

package com.hedgehog.note.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.hedgehog.note.R;
import com.hedgehog.note.ui.activity.AboutActivity;
import com.hedgehog.note.util.ShareUtils;
import com.hedgehog.note.util.SnackbarUtils;

import java.util.List;

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

            Uri uri = Uri.parse("mailto:ciwei@gmail.com");
            final Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            PackageManager pm = getActivity().getPackageManager();
            List<ResolveInfo> infos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (infos == null || infos.size() <= 0) {
                SnackbarUtils.show(getActivity(), R.string.email_notice);

            } else {
                getActivity().startActivity(intent);
            }

        }

        if (TextUtils.equals(key, getString(getActivity(), R.string.preference_rating_app))) {

            String str = "market://details?id=" + getActivity().getPackageName();
            Intent localIntent = new Intent("android.intent.action.VIEW");
            localIntent.setData(Uri.parse(str));
            try {
                startActivity(localIntent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                SnackbarUtils.show(getActivity(), R.string.rating_notice);
            }
        }

        if (TextUtils.equals(key, getString(getActivity(), R.string.preference_share_app))) {
            ShareUtils.share(getActivity());
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

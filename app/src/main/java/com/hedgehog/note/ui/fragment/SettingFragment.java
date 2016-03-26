package com.hedgehog.note.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hedgehog.note.R;
import com.hedgehog.note.adapter.ColorsListAdapter;
import com.hedgehog.note.util.DialogUtils;
import com.hedgehog.note.util.PreferenceUtils;
import com.hedgehog.note.util.SnackbarUtils;
import com.hedgehog.note.util.ThemeUtils;

import java.util.Arrays;
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
        String key=preference.getKey();
        if(TextUtils.equals(key,getString(getActivity(),R.string.preference_darktheme))){
            SnackbarUtils.show(getActivity(),R.string.about);
        }

        if(TextUtils.equals(key,getString(getActivity(),R.string.preference_change_color))){
            showThemeChooseDialog();
        }

        if(TextUtils.equals(key,getString(getActivity(),R.string.preference_feedback))){
            SnackbarUtils.show(getActivity(),R.string.about);
        }

        if(TextUtils.equals(key,getString(getActivity(),R.string.preference_rating_app))){
            SnackbarUtils.show(getActivity(),R.string.about);
        }

        if(TextUtils.equals(key,getString(getActivity(),R.string.preference_about))){
            SnackbarUtils.show(getActivity(),R.string.about);
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


    private String getString(Context context, @StringRes int string){
        if (context != null)
            return context.getString(string);
        return "";
    }


    public void showThemeChooseDialog(){
        AlertDialog.Builder builder = DialogUtils.makeDialogBuilder(getActivity());
        builder.setTitle(R.string.change_app_theme);
        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
                R.drawable.pink_round, R.drawable.green_round};
        List<Integer> list = Arrays.asList(res);
        ColorsListAdapter adapter = new ColorsListAdapter(getActivity(), list);
        adapter.setCheckItem(ThemeUtils.getCurrentTheme(getActivity()).getIntValue());
        GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int value = ThemeUtils.getCurrentTheme(getActivity()).getIntValue();
                if (value != position) {
                    PreferenceUtils.putInt(getActivity(), getActivity().getString(R.string.change_app_theme), position);
//                    notifyChangeTheme();
                }
            }
        });
    }
}

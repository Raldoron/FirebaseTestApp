package com.example.raldoron.firebasetestapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.raldoron.firebasetestapp.R;

/**
 * Created by Raldoron on 18.04.17.
 */

public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    public static AboutFragment getInstance() {
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.setArguments(new Bundle());
        return aboutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView version = (TextView) view.findViewById(R.id.versionTextView);
        version.setText(R.string.version);

        TextView about = (TextView) view.findViewById(R.id.aboutTextView);
        about.setText(R.string.app_name);
        return view;
    }
}

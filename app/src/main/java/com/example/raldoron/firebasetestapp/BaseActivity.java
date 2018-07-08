package com.example.raldoron.firebasetestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by Raldoron on 29.06.17.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public abstract int getLayoutId();
    public abstract ProgressBar getProgressBar();

    @BindView(R.id.app_toolbar)
    Toolbar toolbar;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        progressBar = getProgressBar();

        setTitle(this.getResources().getString(R.string.app_name));

    }


    public void addDrawerListenerTo(DrawerLayout drawerLayout) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void setTitle(CharSequence title) {
        toolbar.setTitle(title);
    }

    public void showWaitDialog() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideWaitDialog() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.GONE);
    }

}

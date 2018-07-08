package com.example.raldoron.firebasetestapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.raldoron.firebasetestapp.Fragments.AboutFragment;
import com.example.raldoron.firebasetestapp.Fragments.ProfileFragment;
import com.example.raldoron.firebasetestapp.Fragments.QuotesList.QuotesListFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer)
    RecyclerView navRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private NetworkStateReceiver networkStateReceiver;
    private IntentFilter intentFilter;

    private int currentFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addDrawerListenerTo(drawerLayout);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        navRecyclerView.setLayoutManager(layoutManager);

        networkStateReceiver = new NetworkStateReceiver();
        intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, QuotesListFragment.getInstance());
        transaction.commitAllowingStateLoss();
        currentFragment = 2;

        RecyclerView.Adapter adapter = new NavigationAdapter(getResources().getStringArray(R.array.drawer),
                (view, position) -> {
                    drawerLayout.closeDrawer(navRecyclerView);
                    if (position == 1 && currentFragment != 1) {
                        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                        t.replace(R.id.content, ProfileFragment.getInstance());
                        t.commitAllowingStateLoss();
                        currentFragment = 1;
                    } else if (position == 2 && currentFragment != 2) {
                        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                        t.replace(R.id.content, QuotesListFragment.getInstance());
                        t.commitAllowingStateLoss();
                        currentFragment = 2;
                    } else if (position == 3 && currentFragment != 3) {
                        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                        t.replace(R.id.content, AboutFragment.getInstance());
                        t.commitAllowingStateLoss();
                        currentFragment = 3;
                    }
                });
        navRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(networkStateReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(networkStateReceiver);
    }
}

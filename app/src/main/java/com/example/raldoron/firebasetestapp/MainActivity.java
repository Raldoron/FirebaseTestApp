package com.example.raldoron.firebasetestapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        recyclerView = (RecyclerView) findViewById(R.id.left_drawer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, QuotesListFragment.getInstance());
        transaction.commitAllowingStateLoss();

        adapter = new NavigationAdapter(getResources().getStringArray(R.array.drawer),
                new NavigationOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                drawerLayout.closeDrawer(recyclerView);
                if (position == 1){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, ProfileFragment.getInstance());
                    transaction.commitAllowingStateLoss();
                }
                else if (position == 2){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, QuotesListFragment.getInstance());
                    transaction.commitAllowingStateLoss();
                }
                else if (position == 3){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, AboutFragment.getInstance());
                    transaction.commitAllowingStateLoss();
                }
            }
        });
        recyclerView.setAdapter(adapter);

    }

}

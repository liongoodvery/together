package org.lion.alloytimer.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.lion.alloytimer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Drawer {


    @BindView(R.id.containner)
    FrameLayout mContainner;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initFragment(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_alloy) {
            switchContent(new AlloyFragment());
        } else if (id == R.id.nav_gist) {
            switchContent(new GistFragment());

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            switchContent(new AlloyFragment());
        } else {


        }
    }

    public void switchContent(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containner, fragment).commit();
        invalidateOptionsMenu();
    }

    @Override
    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }
}

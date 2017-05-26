package com.teatime.teatime_demo.UI;

/**
 * Created by HG on 2017/5/18.
 */

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.teatime.library_teatime.Utils.ViewAnimator;
import com.teatime.library_teatime.interfaces.Resourceble;
import com.teatime.library_teatime.interfaces.ScreenShotable;
import com.teatime.library_teatime.model.SlideMenuItem;
import com.teatime.teatime_demo.R;
import com.teatime.teatime_demo.fragment.ContentFragment;
import com.teatime.teatime_demo.fragment.OneFragment;
import com.teatime.teatime_demo.fragment.ReFreshFragment;
import com.teatime.teatime_demo.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.content_music;
    private LinearLayout linearLayout;
    private FragmentTransaction transaction;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ReFreshFragment reFreshfragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        contentFragment = ContentFragment.newInstance(R.drawable.content_music);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content_frame, contentFragment)
//                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        fragmentManager=getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.content_frame, contentFragment).commit();

        IntFragment();

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
    }

    private void IntFragment() {
        transaction = fragmentManager.beginTransaction();
        oneFragment=new OneFragment();
        twoFragment=new TwoFragment();
        reFreshfragment=new ReFreshFragment();

        transaction.add(R.id.content_frame, oneFragment, ContentFragment.BUILDING);
        transaction.add(R.id.content_frame, twoFragment, ContentFragment.BOOK);
        transaction.add(R.id.content_frame, reFreshfragment, ContentFragment.PAINT);
        transaction.show(oneFragment).hide(twoFragment).hide(reFreshfragment);
        transaction.commit();
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.BUILDING, R.drawable.icn_1);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.icn_2);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.PAINT, R.drawable.icn_3);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CASE, R.drawable.icn_4);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.icn_5);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.MOVIE, R.drawable.icn_7);
        list.add(menuItem7);
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed . */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
//        this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
//        View view = findViewById(R.id.content_frame);
//        int finalRadius = Math.max(view.getWidth(), view.getHeight());
//        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//        animator.setInterpolator(new AccelerateInterpolator());
//        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//
//        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//        animator.start();
//        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
//        return contentFragment;
//    }

    private void  intf(){
        FragmentTransaction  transaction = fragmentManager.beginTransaction();



        transaction.hide(oneFragment).hide(twoFragment).hide(reFreshfragment);
        transaction.commit();
    }



    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        FragmentTransaction  transaction = fragmentManager.beginTransaction();
        intf();
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;

            case ContentFragment.BUILDING:
                transaction.show(oneFragment);
                transaction.commit();
//                OneFragment oneFragment=new OneFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, oneFragment).commit();
                return oneFragment;
            case ContentFragment.BOOK:
                transaction.show(twoFragment);
                transaction.commit();
                return twoFragment;
            case ContentFragment.PAINT:

                transaction.show(twoFragment);
                transaction.commit();
//                transaction.show(reFreshfragment);
//                transaction.commit();
                return reFreshfragment;

            default:
                return null;
//                return replaceFragment(screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
}


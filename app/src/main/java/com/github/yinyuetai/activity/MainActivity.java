package com.github.yinyuetai.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Window;
import android.view.WindowManager;

import com.github.yinyuetai.R;
import com.github.yinyuetai.fragment.FirstPageFragment;
import com.github.yinyuetai.fragment.MVFragment;
import com.github.yinyuetai.fragment.VChartFragment;
import com.github.yinyuetai.fragment.YueDanFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private SparseArray<Fragment> fragments;
    private SparseArray<Integer> colors;
    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        toolBar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        fragments = new SparseArray<>();
        colors = new SparseArray<>();
        fragments.put(R.id.bottomBarItem1, new FirstPageFragment());
        fragments.put(R.id.bottomBarItem2, new MVFragment());
        fragments.put(R.id.bottomBarItem3, new VChartFragment());
        fragments.put(R.id.bottomBarItem4, new YueDanFragment());
        colors.put(R.id.bottomBarItem1,ContextCompat.getColor(this, R.color.tab_color_1));
        colors.put(R.id.bottomBarItem2,ContextCompat.getColor(this, R.color.tab_color_2));
        colors.put(R.id.bottomBarItem3,ContextCompat.getColor(this, R.color.tab_color_3));
        colors.put(R.id.bottomBarItem4,ContextCompat.getColor(this, R.color.tab_color_4));
        BottomBar mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottom_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                Fragment targetFragment = fragments.get(menuItemId);
                setFragment(targetFragment);
                toolBar.setBackgroundColor(colors.get(menuItemId));
                setTranslucenttatus(colors.get(menuItemId));
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.tab_color_1));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.tab_color_2));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.tab_color_3));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.tab_color_4));
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment.isAdded() && fragment.isVisible()) {
            return;
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.replace(R.id.content, fragment);
        }
        transaction.commit();
    }
    private void setTranslucenttatus(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // create our manager instance after the content view is set
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setTintColor(color);
        }

    }
}

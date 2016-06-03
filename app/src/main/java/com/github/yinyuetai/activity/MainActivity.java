package com.github.yinyuetai.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.github.yinyuetai.R;
import com.github.yinyuetai.fragment.FirstPageFragment;
import com.github.yinyuetai.fragment.MVFragment;
import com.github.yinyuetai.fragment.VChartFragment;
import com.github.yinyuetai.fragment.YueDanFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.setting_icon)
    ImageView settingIcon;
    private SparseArray<Fragment> fragments;
    private SparseArray<Integer> colors;
    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        fragments = new SparseArray<>();
        colors = new SparseArray<>();
        fragments.put(R.id.bottomBarItem1, new FirstPageFragment());
        fragments.put(R.id.bottomBarItem2, new MVFragment());
        fragments.put(R.id.bottomBarItem3, new VChartFragment());
        fragments.put(R.id.bottomBarItem4, new YueDanFragment());
        colors.put(R.id.bottomBarItem1, ContextCompat.getColor(this, R.color.tab_color_1));
        colors.put(R.id.bottomBarItem2, ContextCompat.getColor(this, R.color.tab_color_2));
        colors.put(R.id.bottomBarItem3, ContextCompat.getColor(this, R.color.tab_color_3));
        colors.put(R.id.bottomBarItem4, ContextCompat.getColor(this, R.color.tab_color_4));
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
        settingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
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

    @Override
    public void onBackPressed() {
        new AlertDialogWrapper.Builder(this)
                .setTitle("Hi~")
                .setMessage("客官，真的要走吗？")
                .setNegativeButton("点错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("嗯", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }
}

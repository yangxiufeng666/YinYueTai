package com.github.yinyuetai.main;

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
import com.github.yinyuetai.activity.BaseActivity;
import com.github.yinyuetai.homepage.HomePageFragment;
import com.github.yinyuetai.mv.MVFragment;
import com.github.yinyuetai.setting.SettingActivity;
import com.github.yinyuetai.vchart.VChartFragment;
import com.github.yinyuetai.yuedan.YueDanFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.setting_icon)
    ImageView settingIcon;
    private SparseArray<Fragment> fragments;
    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        fragments = new SparseArray<>();
        fragments.put(R.id.bottomBarItem1, new HomePageFragment());
        fragments.put(R.id.bottomBarItem2, new MVFragment());
        fragments.put(R.id.bottomBarItem3, new VChartFragment());
        fragments.put(R.id.bottomBarItem4, new YueDanFragment());
        BottomBar mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottom_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                Fragment targetFragment = fragments.get(menuItemId);
                setFragment(targetFragment);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.colorPrimary));
        settingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
        setTranslucenttatus(ContextCompat.getColor(this, R.color.colorPrimaryDark));
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
